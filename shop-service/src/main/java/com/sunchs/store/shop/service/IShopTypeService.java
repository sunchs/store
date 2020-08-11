package com.sunchs.store.shop.service;

import com.sunchs.store.shop.bean.ShopTypeParam;

public interface IShopTypeService {



    /**
     * 新增、编辑 分类信息
     */
    void save(ShopTypeParam param);

    /**
     * 修改分类状态
     * 0、停用；1、启用；2、删除
     */
    void updateStatus(Integer typeId, Integer status);
}
