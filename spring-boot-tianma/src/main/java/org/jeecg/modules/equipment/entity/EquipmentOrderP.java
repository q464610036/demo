package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养工单点位列表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@TableName("CT_TMOS_EQUIPMENT_ORDER_P")
public class EquipmentOrderP implements Serializable {

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
     * 设备
     */
    private String unitId;

    /**
     * 子设备
     */
    private String subUnitId;

    /**
     * 保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败
     */
    private String upkeepResult;

    /**
     * 保养人code
     */
    private String upkeepUserCode;

    /**
     * 保养人名称
     */
    private String upkeepUserName;

    /**
     * 点位id
     */
    private String pointId;

    /**
     * 点位名称
     */
    private String pointName;

    /**
     * 最小周期
     */
    private String minCycle;

    /**
     * 寿命/天
     */
    private Integer life;

    /**
     * 数量
     */
    private Long num;

    /**
     * 预计更换时间
     */
    private LocalDateTime expectReplaceTime;

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

    public String getUpkeepResult() {
        return upkeepResult;
    }

    public void setUpkeepResult(String upkeepResult) {
        this.upkeepResult = upkeepResult;
    }

    public String getUpkeepUserCode() {
        return upkeepUserCode;
    }

    public void setUpkeepUserCode(String upkeepUserCode) {
        this.upkeepUserCode = upkeepUserCode;
    }

    public String getUpkeepUserName() {
        return upkeepUserName;
    }

    public void setUpkeepUserName(String upkeepUserName) {
        this.upkeepUserName = upkeepUserName;
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

    public String getMinCycle() {
        return minCycle;
    }

    public void setMinCycle(String minCycle) {
        this.minCycle = minCycle;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public LocalDateTime getExpectReplaceTime() {
        return expectReplaceTime;
    }

    public void setExpectReplaceTime(LocalDateTime expectReplaceTime) {
        this.expectReplaceTime = expectReplaceTime;
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
        return "EquipmentOrderP{" +
            "id = " + id +
            ", orderNo = " + orderNo +
            ", unitId = " + unitId +
            ", subUnitId = " + subUnitId +
            ", upkeepResult = " + upkeepResult +
            ", upkeepUserCode = " + upkeepUserCode +
            ", upkeepUserName = " + upkeepUserName +
            ", pointId = " + pointId +
            ", pointName = " + pointName +
            ", minCycle = " + minCycle +
            ", life = " + life +
            ", num = " + num +
            ", expectReplaceTime = " + expectReplaceTime +
            ", status = " + status +
            ", remark = " + remark +
            ", createBy = " + createBy +
            ", createTime = " + createTime +
            ", updateBy = " + updateBy +
            ", updateTime = " + updateTime +
        "}";
    }
}
