package com.sunchs.store.shop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sunchs.store.db.business.entity.Shop;
import com.sunchs.store.db.business.entity.ShopExtend;
import com.sunchs.store.db.business.entity.ShopImage;
import com.sunchs.store.db.business.service.impl.ShopExtendServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopImageServiceImpl;
import com.sunchs.store.db.business.service.impl.ShopServiceImpl;
import com.sunchs.store.shop.bean.ShopExtendParam;
import com.sunchs.store.shop.bean.ShopImageParam;
import com.sunchs.store.shop.bean.ShopParam;
import com.sunchs.store.shop.service.IShopService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopServiceImpl shopDBService;
    @Autowired
    private ShopExtendServiceImpl shopExtendService;
    @Autowired
    private ShopImageServiceImpl shopImageService;

    @Override
    public void save(ShopParam param) {
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
        }
    }

    /**
     * 保存商品扩展信息
     */
    private void saveShopExtend(ShopParam param, Integer shopId) {
        // 清理历史数据
        Wrapper<ShopExtend> wrapper = new EntityWrapper<ShopExtend>()
                .eq(ShopExtend.SHOP_ID, shopId);
        shopExtendService.delete(wrapper);
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
    }

    /**
     * 保存图片信息
     */
    private void saveShopImage(ShopParam param, Integer shopId) {
        // 清理历史数据
        Wrapper<ShopImage> wrapper = new EntityWrapper<ShopImage>()
                .eq(ShopImage.SHOP_ID, shopId);
        shopImageService.delete(wrapper);
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
    }
}
