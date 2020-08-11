package com.sunchs.store.db.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产品图片表
 * </p>
 *
 * @author king
 * @since 2020-08-11
 */
@TableName("shop_image")
public class ShopImage extends Model<ShopImage> {

    private static final long serialVersionUID = 1L;

    /**
     * 图片ID
     */
    @TableId(value = "img_id", type = IdType.AUTO)
    private Integer imgId;

    /**
     * 商品ID
     */
    @TableField("shop_id")
    private Integer shopId;

    /**
     * 图片类型，0、原图；1、大图；2、缩略图 ...
     */
    private Integer type;

    /**
     * 图片路径
     */
    private String path;

    /**
     * 图片宽度
     */
    private Integer width;

    /**
     * 图片高度
     */
    private Integer height;

    /**
     * 图片排序
     */
    private Integer sort;

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public static final String IMG_ID = "img_id";

    public static final String SHOP_ID = "shop_id";

    public static final String TYPE = "type";

    public static final String PATH = "path";

    public static final String WIDTH = "width";

    public static final String HEIGHT = "height";

    public static final String SORT = "sort";

    @Override
    protected Serializable pkVal() {
        return this.imgId;
    }

    @Override
    public String toString() {
        return "ShopImage{" +
        "imgId=" + imgId +
        ", shopId=" + shopId +
        ", type=" + type +
        ", path=" + path +
        ", width=" + width +
        ", height=" + height +
        ", sort=" + sort +
        "}";
    }
}
