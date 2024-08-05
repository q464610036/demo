package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 借机表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-05
 */
@TableName("CT_TMOS_EQUIPMENT_BORROW")
public class EquipmentBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 借机单号
     */
    private String borrowNo;

    /**
     * 借机人
     */
    private String borrowUserCode;

    /**
     * 借机人名称
     */
    private String borrowUserName;

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
     * 停线类型：1=Line，2=Unit(不停线)
     */
    private String stopLineType;

    /**
     * 借机时间
     */
    private LocalDateTime borrowTime;

    /**
     * 计划借机时长
     */
    private BigDecimal borrowPlanHours;

    /**
     * 班别：1=白班，2=夜班
     */
    private String shiftType;

    /**
     * 借机模式：1=普通借机，2=紧急借机
     */
    private String borrowMode;

    /**
     * 借机类型：ENG=工程借机，F_ENG=实验借机，PM=保养借机，EQ_D=宕机保养
     */
    private String borrowType;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

    /**
     * 审批状态: 01=审核中，02=审核通过/待使用，03=使用中，04=使用完毕，05=审核不通过，06=作废
     */
    private String approveStatus;

    /**
     * 实际借机开始时间
     */
    private LocalDateTime borrowRealStartTime;

    /**
     * 实际借机结束时间
     */
    private LocalDateTime borrowRealEndTime;

    /**
     * 实际借机时间
     */
    private Integer borrowRealHours;

    /**
     * 借机原因
     */
    private String borrowReason;

    /**
     * 主管用户code
     */
    private String leaderUserCode;

    /**
     * 组织名称
     */
    private String officeName;

    /**
     * 组织code
     */
    private String officeCode;

    /**
     * 生产计划用户code
     */
    private String productPlanUserCode;

    /**
     * 状态 0=正常，1=删除
     */
    private String status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主管用户名称
     */
    private String leaderUserName;

    /**
     * 生产计划用户名称
     */
    private String productPlanUserName;

    /**
     * 当前节点
     */
    private String nodeCode;

    /**
     * 审批时间
     */
    private LocalDateTime approveTime;

    /**
     * 子设备
     */
    private String subUnitId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrowNo() {
        return borrowNo;
    }

    public void setBorrowNo(String borrowNo) {
        this.borrowNo = borrowNo;
    }

    public String getBorrowUserCode() {
        return borrowUserCode;
    }

    public void setBorrowUserCode(String borrowUserCode) {
        this.borrowUserCode = borrowUserCode;
    }

    public String getBorrowUserName() {
        return borrowUserName;
    }

    public void setBorrowUserName(String borrowUserName) {
        this.borrowUserName = borrowUserName;
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

    public String getStopLineType() {
        return stopLineType;
    }

    public void setStopLineType(String stopLineType) {
        this.stopLineType = stopLineType;
    }

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public BigDecimal getBorrowPlanHours() {
        return borrowPlanHours;
    }

    public void setBorrowPlanHours(BigDecimal borrowPlanHours) {
        this.borrowPlanHours = borrowPlanHours;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public String getBorrowMode() {
        return borrowMode;
    }

    public void setBorrowMode(String borrowMode) {
        this.borrowMode = borrowMode;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public LocalDateTime getBorrowRealStartTime() {
        return borrowRealStartTime;
    }

    public void setBorrowRealStartTime(LocalDateTime borrowRealStartTime) {
        this.borrowRealStartTime = borrowRealStartTime;
    }

    public LocalDateTime getBorrowRealEndTime() {
        return borrowRealEndTime;
    }

    public void setBorrowRealEndTime(LocalDateTime borrowRealEndTime) {
        this.borrowRealEndTime = borrowRealEndTime;
    }

    public Integer getBorrowRealHours() {
        return borrowRealHours;
    }

    public void setBorrowRealHours(Integer borrowRealHours) {
        this.borrowRealHours = borrowRealHours;
    }

    public String getBorrowReason() {
        return borrowReason;
    }

    public void setBorrowReason(String borrowReason) {
        this.borrowReason = borrowReason;
    }

    public String getLeaderUserCode() {
        return leaderUserCode;
    }

    public void setLeaderUserCode(String leaderUserCode) {
        this.leaderUserCode = leaderUserCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getProductPlanUserCode() {
        return productPlanUserCode;
    }

    public void setProductPlanUserCode(String productPlanUserCode) {
        this.productPlanUserCode = productPlanUserCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLeaderUserName() {
        return leaderUserName;
    }

    public void setLeaderUserName(String leaderUserName) {
        this.leaderUserName = leaderUserName;
    }

    public String getProductPlanUserName() {
        return productPlanUserName;
    }

    public void setProductPlanUserName(String productPlanUserName) {
        this.productPlanUserName = productPlanUserName;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public LocalDateTime getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(LocalDateTime approveTime) {
        this.approveTime = approveTime;
    }

    public String getSubUnitId() {
        return subUnitId;
    }

    public void setSubUnitId(String subUnitId) {
        this.subUnitId = subUnitId;
    }

    @Override
    public String toString() {
        return "EquipmentBorrow{" +
            "id = " + id +
            ", borrowNo = " + borrowNo +
            ", borrowUserCode = " + borrowUserCode +
            ", borrowUserName = " + borrowUserName +
            ", factoryName = " + factoryName +
            ", area = " + area +
            ", eqpId = " + eqpId +
            ", unitId = " + unitId +
            ", stopLineType = " + stopLineType +
            ", borrowTime = " + borrowTime +
            ", borrowPlanHours = " + borrowPlanHours +
            ", shiftType = " + shiftType +
            ", borrowMode = " + borrowMode +
            ", borrowType = " + borrowType +
            ", submitTime = " + submitTime +
            ", approveStatus = " + approveStatus +
            ", borrowRealStartTime = " + borrowRealStartTime +
            ", borrowRealEndTime = " + borrowRealEndTime +
            ", borrowRealHours = " + borrowRealHours +
            ", borrowReason = " + borrowReason +
            ", leaderUserCode = " + leaderUserCode +
            ", officeName = " + officeName +
            ", officeCode = " + officeCode +
            ", productPlanUserCode = " + productPlanUserCode +
            ", status = " + status +
            ", createBy = " + createBy +
            ", createTime = " + createTime +
            ", updateBy = " + updateBy +
            ", updateTime = " + updateTime +
            ", remark = " + remark +
            ", leaderUserName = " + leaderUserName +
            ", productPlanUserName = " + productPlanUserName +
            ", nodeCode = " + nodeCode +
            ", approveTime = " + approveTime +
            ", subUnitId = " + subUnitId +
        "}";
    }
}
