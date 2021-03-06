package com.sunchs.store.db.business.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sunchs.store.db.business.entity.Shop;
import com.sunchs.store.db.business.mapper.ShopMapper;
import com.sunchs.store.db.business.service.IShopService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author king
 * @since 2020-08-13
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Override
    public Page<Shop> getPage(Wrapper<Shop> wrapper, Integer pageNow, Integer pageSize) {
        Page<Shop> page = new Page<>(pageNow, pageSize);
        return selectPage(page, wrapper);
    }

    @Override
    public void decrStock(Integer shopId) {
        baseMapper.decrShopStock(shopId);
    }
}
