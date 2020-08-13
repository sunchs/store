package com.sunchs.store.shop.bean;

import com.sunchs.store.framework.bean.PagingParam;
import com.sunchs.store.shop.exception.ShopException;

import java.util.List;
import java.util.Objects;

public class ShopParam extends PagingParam {

    /**
     * 商品ID
     */
    private int shopId;

    /**
     * 商品分类ID
     */
    private int typeId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品系列号
     */
    private String shopSn;

    /**
     * 商品状态，0、停售；1、开售；2、删除
     */
    private Integer status;

    /**
     * 市场价格
     */
    private double marketPrice;

    /**
     * 出售价格
     */
    private double price;

    /**
     * 重量（单位：克）
     */
    private int weight;

    /**
     * 库存
     */
    private int stock;

    /**
     * 库存警告
     */
    private int stockWarning;

    /**
     * 详细内容
     */
    private String content;

    /**
     * 商品图片信息
     */
    List<ShopImageParam> imageList;

    public void filter() {
        if (typeId == 0) {
            throw new ShopException("商品分类不能为空！");
        }
        if (Objects.isNull(title) || title.length() == 0) {
            throw new ShopException("商品标题不能为空！");
        } else if (title.length() > 100) {
            throw new ShopException("商品标题不能超过100个字符！");
        }
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
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

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockWarning() {
        return stockWarning;
    }

    public void setStockWarning(int stockWarning) {
        this.stockWarning = stockWarning;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ShopImageParam> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShopImageParam> imageList) {
        this.imageList = imageList;
    }
}
