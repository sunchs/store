package com.sunchs.store.db.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 秒杀活动表
 * </p>
 *
 * @author king
 * @since 2020-08-11
 */
@TableName("flash_sale")
public class FlashSale extends Model<FlashSale> {

    private static final long serialVersionUID = 1L;

    /**
     * 促销ID
     */
    @TableId(value = "sale_id", type = IdType.AUTO)
    private Integer saleId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 状态，0、停用；1、启用；2、删除
     */
    private Integer status;

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static final String SALE_ID = "sale_id";

    public static final String TITLE = "title";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String STATUS = "status";

    @Override
    protected Serializable pkVal() {
        return this.saleId;
    }

    @Override
    public String toString() {
        return "FlashSale{" +
        "saleId=" + saleId +
        ", title=" + title +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", status=" + status +
        "}";
    }
}
