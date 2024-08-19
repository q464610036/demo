package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 点位残件表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@TableName("CT_TMOS_EQUIPMENT_INCOMPLETE_P")
public class EquipmentIncompleteP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 工单号
     */
    private String orderNo;

    /**
     * 厂别
     */
    private String factoryName;

    /**
     * 设备群组
     */
    private String area;

    /**
     * 线体
     */
    private String eqpId;

    /**
     * 设备
     */
    private String unitId;

    /**
     * 子设备
     */
    private String subUnitId;

    /**
     * 周期
     */
    private String cycle;

    /**
     * 点位id
     */
    private String pointId;

    /**
     * 点位名称
     */
    private String pointName;

    /**
     * 保养结果：0=待保养，1=完成，2=未保养，3=残件，4=寿命上限，5=FDC，9=保养失败
     */
    private String upkeepResult;

    /**
     * 原计划日期
     */
    private LocalDateTime planTime;

    /**
     * 实际日期
     */
    private LocalDateTime realTime;

    /**
     * 锁定状态：0=待记录，1=审批中，2=执行中
     */
    private String lockStatus;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEqpId() {
        return eqpId;
    }

    public void setEqpId(String eqpId) {
        this.eqpId = eqpId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getSubUnitId() {
        return subUnitId;
    }

    public void setSubUnitId(String subUnitId) {
        this.subUnitId = subUnitId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getUpkeepResult() {
        return upkeepResult;
    }

    public void setUpkeepResult(String upkeepResult) {
        this.upkeepResult = upkeepResult;
    }

    public LocalDateTime getPlanTime() {
        return planTime;
    }

    public void setPlanTime(LocalDateTime planTime) {
        this.planTime = planTime;
    }

    public LocalDateTime getRealTime() {
        return realTime;
    }

    public void setRealTime(LocalDateTime realTime) {
        this.realTime = realTime;
    }

    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
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
        return "EquipmentIncompleteP{" +
            "id = " + id +
            ", orderNo = " + orderNo +
            ", factoryName = " + factoryName +
            ", area = " + area +
            ", eqpId = " + eqpId +
            ", unitId = " + unitId +
            ", subUnitId = " + subUnitId +
            ", cycle = " + cycle +
            ", pointId = " + pointId +
            ", pointName = " + pointName +
            ", upkeepResult = " + upkeepResult +
            ", planTime = " + planTime +
            ", realTime = " + realTime +
            ", lockStatus = " + lockStatus +
            ", status = " + status +
            ", remark = " + remark +
            ", createBy = " + createBy +
            ", createTime = " + createTime +
            ", updateBy = " + updateBy +
            ", updateTime = " + updateTime +
        "}";
    }
}
