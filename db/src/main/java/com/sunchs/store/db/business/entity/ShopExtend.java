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
 * 商品扩展表
 * </p>
 *
 * @author king
 * @since 2020-08-11
 */
@TableName("shop_extend")
public class ShopExtend extends Model<ShopExtend> {

    private static final long serialVersionUID = 1L;

    /**
     * 扩展ID
     */
    @TableId(value = "ext_id", type = IdType.AUTO)
    private Integer extId;

    /**
     * 商品ID
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 颜色ID
     */
    @TableField("color_id")
    private Integer colorId;

    /**
     * 尺寸ID
     */
    @TableField("size_id")
    private Integer sizeId;

    /**
     * 市场价格
     */
    @TableField("market_price")
    private BigDecimal marketPrice;

    /**
     * 出售价格
     */
    private BigDecimal price;

    /**
     * 重量（单位：克）
     */
    private Integer weight;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存警告
     */
    @TableField("stock_warning")
    private Integer stockWarning;

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }
    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getStockWarning() {
        return stockWarning;
    }

    public void setStockWarning(Integer stockWarning) {
        this.stockWarning = stockWarning;
    }

    public static final String EXT_ID = "ext_id";

    public static final String SHOP_ID = "shop_id";

    public static final String COLOR_ID = "color_id";

    public static final String SIZE_ID = "size_id";

    public static final String MARKET_PRICE = "market_price";

    public static final String PRICE = "price";

    public static final String WEIGHT = "weight";

    public static final String STOCK = "stock";

    public static final String STOCK_WARNING = "stock_warning";

    @Override
    protected Serializable pkVal() {
        return this.extId;
    }

    @Override
    public String toString() {
        return "ShopExtend{" +
        "extId=" + extId +
        ", shopId=" + shopId +
        ", colorId=" + colorId +
        ", sizeId=" + sizeId +
        ", marketPrice=" + marketPrice +
        ", price=" + price +
        ", weight=" + weight +
        ", stock=" + stock +
        ", stockWarning=" + stockWarning +
        "}";
    }
}
