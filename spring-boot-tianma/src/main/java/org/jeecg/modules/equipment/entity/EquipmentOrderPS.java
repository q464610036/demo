package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养工单点位料号列表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-27
 */
@TableName("CT_TMOS_EQUIPMENT_ORDER_P_S")
public class EquipmentOrderPS implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 订单点位id
     */
    private String orderPointId;

    /**
     * 数量
     */
    private Long num;

    /**
     * 料号
     */
    private String stuffNo;

    /**
     * 单品id（多个，逗号隔开）
     */
    private String singleGoodsIds;

    /**
     * 状态 0=正常，1=删除
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderPointId() {
        return orderPointId;
    }

    public void setOrderPointId(String orderPointId) {
        this.orderPointId = orderPointId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getStuffNo() {
        return stuffNo;
    }

    public void setStuffNo(String stuffNo) {
        this.stuffNo = stuffNo;
    }

    public String getSingleGoodsIds() {
        return singleGoodsIds;
    }

    public void setSingleGoodsIds(String singleGoodsIds) {
        this.singleGoodsIds = singleGoodsIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "EquipmentOrderPS{" +
            "id = " + id +
            ", orderPointId = " + orderPointId +
            ", num = " + num +
            ", stuffNo = " + stuffNo +
            ", singleGoodsIds = " + singleGoodsIds +
            ", status = " + status +
            ", remark = " + remark +
            ", createBy = " + createBy +
            ", createTime = " + createTime +
            ", updateBy = " + updateBy +
            ", updateTime = " + updateTime +
        "}";
    }
}
