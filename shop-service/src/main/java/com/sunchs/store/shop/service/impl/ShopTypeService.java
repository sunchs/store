package com.sunchs.store.shop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sunchs.store.db.business.entity.ShopType;
import com.sunchs.store.db.business.service.impl.ShopTypeServiceImpl;
import com.sunchs.store.framework.constants.CacheKeys;
import com.sunchs.store.framework.util.Logger;
import com.sunchs.store.framework.util.RedisClient;
import com.sunchs.store.shop.bean.ShopTypeParam;
import com.sunchs.store.shop.bean.ShopTypeVO;
import com.sunchs.store.shop.service.IShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ShopTypeService implements IShopTypeService {

    @Autowired
    private ShopTypeServiceImpl shopTypeDBService;

    @Override
    public List<ShopTypeVO> getTypeList(ShopTypeParam param) {
        Wrapper<ShopType> wrapper = new EntityWrapper<>();
        if (param.getParentId() > 0) {
            wrapper.eq(ShopType.PID, param.getParentId());
        }
        List<ShopType> shopTypeList = shopTypeDBService.selectList(wrapper);
        return parseData(shopTypeList, 0);
    }

    @Override
    public void save(ShopTypeParam param) {
        try {
            // 参数检查
            param.filter();
            // 保存信息
            ShopType data = new ShopType();
            data.setTypeId(param.getTypeId());
            data.setPid(param.getParentId());
            data.setTitle(param.getTitle());
            data.setStatus(param.getStatus());
            data.setSort(param.getSort());
            if (shopTypeDBService.insertOrUpdate(data)) {
                // 清除缓存
                clearShopTypeCache(data.getTypeId());
            }
        } catch (Exception e) {
            Logger.error("保存产品分类异常，参数["+param+"]", e);
        }
    }

    @Override
    public void updateStatus(Integer typeId, Integer status) {
        try {
            // 更新状态
            ShopType data = new ShopType();
            data.setTypeId(typeId);
            data.setStatus(status);
            shopTypeDBService.updateById(data);
            // 清除缓存
            clearShopTypeCache(typeId);
        } catch (Exception e) {
            Logger.error("修改产品状态异常，参数[typeId:" + typeId + "，status:" + status + "]", e);
        }
    }

    /**
     * 清理商品分类缓存
     */
    private void clearShopTypeCache(Integer typeId) {
        RedisClient.delKey(CacheKeys.SHOP_TYPE_CACHE_KEY + typeId);
    }

    private List<ShopTypeVO> parseData(List<ShopType> ShopTypeList, Integer index) {
        List<ShopTypeVO> list = new ArrayList<>(ShopTypeList.size());
        List<ShopType> typeList = ShopTypeList.stream().filter(v -> Objects.equals(v.getPid(), index)).collect(Collectors.toList());
        typeList.forEach(type -> {
            ShopTypeVO vo = new ShopTypeVO();
            vo.setTypeId(type.getTypeId());
            vo.setTitle(type.getTitle());
            vo.setChildren(parseData(ShopTypeList, type.getTypeId()));
            list.add(vo);
        });
        return list;
    }
}
