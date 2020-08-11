package com.sunchs.store.db.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author king
 * @since 2020-08-11
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
     * 详细内容
     */
    private String content;

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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final String SHOP_ID = "shop_id";

    public static final String TYPE_ID = "type_id";

    public static final String TITLE = "title";

    public static final String SHOP_SN = "shop_sn";

    public static final String STATUS = "status";

    public static final String CONTENT = "content";

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
        ", content=" + content +
        "}";
    }
}
