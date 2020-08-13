package com.sunchs.store.db.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author king
 * @since 2020-08-13
 */
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;

    /**
     * 商品分类ID
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品系列号
     */
    @TableField("shop_sn")
    private String shopSn;

    /**
     * 商品状态，0、停售；1、开售；2、删除
     */
    private Integer status;

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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getShopSn() {
        return shopSn;
    }

    public void setShopSn(String shopSn) {
        this.shopSn = shopSn;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public static final String SHOP_ID = "shop_id";

    public static final String TYPE_ID = "type_id";

    public static final String TITLE = "title";

    public static final String SHOP_SN = "shop_sn";

    public static final String STATUS = "status";

    public static final String MARKET_PRICE = "market_price";

    public static final String PRICE = "price";

    public static final String WEIGHT = "weight";

    public static final String STOCK = "stock";

    public static final String STOCK_WARNING = "stock_warning";

    @Override
    protected Serializable pkVal() {
        return this.shopId;
    }

    @Override
    public String toString() {
        return "Shop{" +
        "shopId=" + shopId +
        ", typeId=" + typeId +
        ", title=" + title +
        ", shopSn=" + shopSn +
        ", status=" + status +
        ", marketPrice=" + marketPrice +
        ", price=" + price +
        ", weight=" + weight +
        ", stock=" + stock +
        ", stockWarning=" + stockWarning +
        "}";
    }
}
