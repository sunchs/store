package com.sunchs.store.shop.service;

import com.sunchs.store.framework.bean.PagingList;
import com.sunchs.store.shop.bean.ShopParam;
import com.sunchs.store.shop.bean.ShopVO;

public interface IShopService {

    /**
     * 获取商品分页
     */
    PagingList<ShopVO> getPageList(ShopParam param);

    /**
     * 新增、编辑 商品信息
     */
    void save(ShopParam param);

    /**
     * 修改商品状态
     * 0、停售；1、开售；2、删除
     */
    void updateStatus(Integer shopId, Integer status);
}
