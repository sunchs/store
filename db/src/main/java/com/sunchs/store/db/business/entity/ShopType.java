package com.sunchs.store.db.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author king
 * @since 2020-08-11
 */
@TableName("shop_type")
public class ShopType extends Model<ShopType> {

    private static final long serialVersionUID = 1L;

    /**
     * 分类ID
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 分类标题
     */
    private String title;

    /**
     * 分类状态，0、停用；1、启用；2、删除
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public static final String TYPE_ID = "type_id";

    public static final String PID = "pid";

    public static final String TITLE = "title";

    public static final String STATUS = "status";

    public static final String SORT = "sort";

    @Override
    protected Serializable pkVal() {
        return this.typeId;
    }

    @Override
    public String toString() {
        return "ShopType{" +
        "typeId=" + typeId +
        ", pid=" + pid +
        ", title=" + title +
        ", status=" + status +
        ", sort=" + sort +
        "}";
    }
}
