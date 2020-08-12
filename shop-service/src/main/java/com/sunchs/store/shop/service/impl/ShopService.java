package com.sunchs.store.shop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sunchs.store.db.business.entity.Shop;
import com.sunchs.store.db.business.entity.ShopExtend;
import com.sunchs.store.db.business.entity.ShopImage;
import com.sunchs.store.db.business.service.impl.ShopExtendServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopImageServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopServiceImpl;
import com.sunchs.store.framework.bean.PagingList;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.enums.ShopStatusEnum;
import com.sunchs.store.framework.util.Logger;
import com.sunchs.store.framework.util.PagingUtil;
import com.sunchs.store.framework.util.RedisClient;
import com.sunchs.store.shop.bean.ShopExtendParam;
import com.sunchs.store.shop.bean.ShopImageParam;
import com.sunchs.store.shop.bean.ShopParam;
import com.sunchs.store.shop.bean.ShopVO;
import com.sunchs.store.shop.service.IShopService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopServiceImpl shopDBService;
    @Autowired
    private ShopExtendServiceImpl shopExtendService;
    @Autowired
    private ShopImageServiceImpl shopImageService;

    @Override
    public PagingList<ShopVO> getPageList(ShopParam param) {
        Wrapper<Shop> wrapper = new EntityWrapper<>();
        // 按商品状态
        if (Objects.nonNull(param.getStatus())) {
            wrapper.eq(Shop.STATUS, param.getStatus());
        } else {
            wrapper.eq(Shop.STATUS, ShopStatusEnum.ENABLE.value);
        }
        // 按商品分类
        if (param.getTypeId() > 0) {
            wrapper.eq(Shop.TYPE_ID, param.getTypeId());
        }
        Page<Shop> page = shopDBService.getPage(wrapper, param.getPageNow(), param.getPageSize());
        List<ShopVO> list = parseData(page.getRecords());
        return PagingUtil.getData(list, page.getTotal(), page.getCurrent(), page.getSize());
    }

    @Override
    public void save(ShopParam param) {
        try {
            // 参数检查
            param.filter();
            Shop data = new Shop();
            data.setShopId(param.getShopId());
            data.setTypeId(param.getTypeId());
            data.setTitle(param.getTitle());
            data.setShopSn(param.getShopSn());
            data.setStatus(param.getStatus());
            data.setContent(param.getContent());
            if (shopDBService.insertOrUpdate(data)) {
                // 保存商品扩展信息
                saveShopExtend(param, data.getShopId());
                // 保存图片信息
                saveShopImage(param, data.getShopId());
                // 清除缓存
                clearShopCache(data.getShopId());
            }
        } catch (Exception e) {
            Logger.error("保存产品异常，参数["+param+"]", e);
        }
    }

    @Override
    public void updateStatus(Integer shopId, Integer status) {
        try {
            // 更新状态
            Shop data = new Shop();
            data.setShopId(shopId);
            data.setStatus(status);
            shopDBService.updateById(data);
            // 清除缓存
            clearShopCache(shopId);
        } catch (Exception e) {
            Logger.error("修改产品状态异常，参数[shopId:" + shopId + "，status:" + status + "]", e);
        }
    }

    /**
     * 保存商品扩展信息
     */
    private void saveShopExtend(ShopParam param, Integer shopId) {
        try {
            // 清理历史数据
            if (param.getShopId() > 0) {
                Wrapper<ShopExtend> wrapper = new EntityWrapper<ShopExtend>()
                        .eq(ShopExtend.SHOP_ID, shopId);
                shopExtendService.delete(wrapper);
            }
            // 存储扩展信息
            List<ShopExtendParam> list = param.getExtendList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (ShopExtendParam ext : list) {
                    ShopExtend data = new ShopExtend();
                    data.setShopId(shopId);
                    data.setColorId(ext.getExtId());
                    data.setSizeId(ext.getSizeId());
                    data.setMarketPrice(ext.getMarketPrice());
                    data.setPrice(ext.getPrice());
                    data.setWeight(ext.getWeight());
                    data.setStock(ext.getStock());
                    data.setStockWarning(ext.getStockWarning());
                    shopExtendService.insert(data);
                }
            }
        } catch (Exception e) {
            Logger.error("保存商品扩展信息异常，参数["+param+"]", e);
        }
    }

    /**
     * 保存图片信息
     */
    private void saveShopImage(ShopParam param, Integer shopId) {
        try {
            // 清理历史数据
            if (param.getShopId() > 0) {
                Wrapper<ShopImage> wrapper = new EntityWrapper<ShopImage>()
                        .eq(ShopImage.SHOP_ID, shopId);
                shopImageService.delete(wrapper);
            }
            // 存储图片信息
            List<ShopImageParam> list = param.getImageList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (ShopImageParam img : list) {
                    ShopImage data = new ShopImage();
                    data.setShopId(shopId);
                    data.setType(img.getType());
                    data.setPath(img.getPath());
                    // 暂时不做宽度高度计算
                    data.setWidth(0);
                    data.setHeight(0);
                    data.setSort(img.getSort());
                    shopImageService.insert(data);
                }
            }
        } catch (Exception e) {
            Logger.error("保存商品图片信息异常，参数["+param+"]", e);
        }
    }

    /**
     * 清理商品缓存
     */
    private void clearShopCache(Integer shopId) {
        RedisClient.delKey(CacheKeys.SHOP_CACHE_KEY + shopId);
    }

    /**
     * 格式化数据
     */
    private List<ShopVO> parseData(List<Shop> shopList) {
        List<ShopVO> list = new ArrayList<>(shopList.size());
        shopList.forEach(s -> {
            ShopVO vo = new ShopVO();
            vo.setShopId(s.getShopId());
            vo.setTypeId(s.getTypeId());
            vo.setTitle(s.getTitle());
            vo.setShopSn(s.getShopSn());
            vo.setContent(s.getContent());
            list.add(vo);
        });
        return list;
    }
}
