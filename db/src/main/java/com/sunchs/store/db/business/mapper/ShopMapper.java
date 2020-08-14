package com.sunchs.store.db.business.mapper;

import com.sunchs.store.db.business.entity.Shop;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author king
 * @since 2020-08-13
 */
public interface ShopMapper extends BaseMapper<Shop> {

    @Update("UPDATE shop SET stock=stock-1 WHERE shop_id=${shopId}")
    boolean decrShopStock(@Param("shopId") Integer shopId);
}
