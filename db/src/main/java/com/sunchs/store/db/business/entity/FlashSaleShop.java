package com.sunchs.store.db.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 秒杀活动产品表
 * </p>
 *
 * @author king
 * @since 2020-08-14
 */
@TableName("flash_sale_shop")
public class FlashSaleShop extends Model<FlashSaleShop> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 促销ID
     */
    @TableField("sale_id")
    private Integer saleId;

    /**
     * 商品ID
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 出售金额
     */
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static final String ID = "id";

    public static final String SALE_ID = "sale_id";

    public static final String SHOP_ID = "shop_id";

    public static final String PRICE = "price";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FlashSaleShop{" +
        "id=" + id +
        ", saleId=" + saleId +
        ", shopId=" + shopId +
        ", price=" + price +
        "}";
    }
}
