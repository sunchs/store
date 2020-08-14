package com.sunchs.store.shop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sunchs.store.db.business.entity.Shop;
import com.sunchs.store.db.business.entity.ShopContent;
import com.sunchs.store.db.business.entity.ShopImage;
import com.sunchs.store.db.business.service.impl.ShopContentServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopImageServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopServiceImpl;
import com.sunchs.store.framework.bean.PagingList;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.data.DataReader;
import com.sunchs.store.framework.data.Logger;
import com.sunchs.store.framework.data.RedisClient;
import com.sunchs.store.framework.enums.ShopStatusEnum;
import com.sunchs.store.framework.util.PagingUtil;
import com.sunchs.store.shop.bean.ShopImageParam;
import com.sunchs.store.shop.bean.ShopImageVO;
import com.sunchs.store.shop.bean.ShopParam;
import com.sunchs.store.shop.bean.ShopVO;
import com.sunchs.store.shop.service.IShopService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopServiceImpl shopDBService;
    @Autowired
    private ShopContentServiceImpl shopContentService;
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
            // 保存信息
            Shop data = new Shop();
            data.setShopId(param.getShopId());
            data.setTypeId(param.getTypeId());
            data.setTitle(param.getTitle());
            data.setShopSn(param.getShopSn());
            data.setStatus(param.getStatus());
            data.setMarketPrice(new BigDecimal(param.getMarketPrice()));
            data.setPrice(new BigDecimal(param.getPrice()));
            data.setWeight(param.getWeight());
            data.setStock(param.getStock());
            data.setStockWarning(param.getStockWarning());
            if (shopDBService.insertOrUpdate(data)) {
                // 保存商品详细内容
                saveShopContent(param, data.getShopId());
                // 保存图片信息
                saveShopImage(param, data.getShopId());
                // 清除缓存
                clearShopCache(data.getShopId());
                // 更新商品库存
                RedisClient.setValue(CacheKeys.SHOP_STOCK + data.getShopId(), param.getStock() + "");
            }
        } catch (Exception e) {
            Logger.error("保存商品信息异常，参数["+param+"]", e);
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
            Logger.error("修改商品状态异常，参数[shopId:" + shopId + "，status:" + status + "]", e);
        }
    }

    /**
     * 保存商品详细信息
     */
    private void saveShopContent(ShopParam param, Integer shopId) {
        try {
            ShopContent data = new ShopContent();
            data.setShopId(shopId);
            data.setContent(param.getContent());
            shopContentService.insertOrUpdate(data);
        } catch (Exception e) {
            Logger.error("保存商品详细内容异常，参数["+param+"]", e);
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
            Logger.error("保存商品图片异常，参数["+param+"]", e);
        }
    }

    /**
     * 清理商品缓存
     */
    private void clearShopCache(Integer shopId) {
        RedisClient.delKey(CacheKeys.SHOP + shopId);
        RedisClient.delKey(CacheKeys.SHOP_CONTENT + shopId);
        RedisClient.delKey(CacheKeys.SHOP_IMAGE + shopId);
    }

    /**
     * 格式化数据
     */
    private List<ShopVO> parseData(List<Shop> shopList) {
        List<ShopVO> list = new ArrayList<>(shopList.size());
        shopList.forEach(shop -> {
            ShopVO vo = new ShopVO();
            vo.setShopId(shop.getShopId());
            vo.setTypeId(shop.getTypeId());
            vo.setTitle(shop.getTitle());
            vo.setShopSn(shop.getShopSn());
            vo.setMarketPrice(shop.getMarketPrice().doubleValue());
            vo.setPrice(shop.getPrice().doubleValue());
            vo.setWeight(shop.getWeight());
            vo.setStock(shop.getStock());
            vo.setStockWarning(shop.getStockWarning());
            // 详细内容
            ShopContent shopContent = getShopContent(shop.getShopId());
            if (Objects.nonNull(shopContent)) {
                vo.setContent(shopContent.getContent());
            }
            // 图片信息
            List<ShopImageVO> imagelist = new ArrayList<>();
            getShopImageList(shop.getShopId()).forEach(img -> {
                ShopImageVO imageVO = new ShopImageVO();
                imageVO.setType(img.getType());
                imageVO.setPath(img.getPath());
                imagelist.add(imageVO);
            });
            vo.setImageList(imagelist);
            list.add(vo);
        });
        return list;
    }

    /**
     * 获取商品详细内容
     */
    public ShopContent getShopContent(Integer shopId) {
         return DataReader.getData(CacheKeys.SHOP_CONTENT + shopId, ShopContent.class, () -> {
            Wrapper<ShopContent> wrapper = new EntityWrapper<ShopContent>()
                    .setSqlSelect(ShopContent.CONTENT)
                    .eq(ShopContent.SHOP_ID, shopId);
             return shopContentService.selectOne(wrapper);
         });
    }

    /**
     * 获取商品图片数据
     */
    public List<ShopImage> getShopImageList(Integer shopId) {
        return DataReader.getListData(CacheKeys.SHOP_IMAGE + shopId, ShopImage.class, () -> {
            Wrapper<ShopImage> wrapper = new EntityWrapper<ShopImage>()
                    .eq(ShopImage.SHOP_ID, shopId);
            return shopImageService.selectList(wrapper);
        });
    }
}
