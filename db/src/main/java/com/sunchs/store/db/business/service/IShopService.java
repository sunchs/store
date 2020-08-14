package com.sunchs.store.db.business.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sunchs.store.db.business.entity.Shop;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author king
 * @since 2020-08-13
 */
public interface IShopService extends IService<Shop> {

    /**
     * 分页查询
     */
    Page<Shop> getPage(Wrapper<Shop> wrapper, Integer pageNow, Integer pageSize);

    /**
     * 减少库存
     */
    void decrStock(Integer shopId);
}
