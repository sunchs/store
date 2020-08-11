package com.sunchs.store.db.business.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.sunchs.store.db.business.entity.Shop;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author king
 * @since 2020-08-11
 */
public interface IShopService extends IService<Shop> {

    /**
     * 分页查询
     */
    Page<Shop> getPage(Wrapper<Shop> wrapper, Integer pageNow, Integer pageSize);
}
