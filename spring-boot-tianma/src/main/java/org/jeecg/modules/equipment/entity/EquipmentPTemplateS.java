package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 点位模版点位物料表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@TableName("CT_TMOS_EQUIPMENT_P_TEMPLATE_S")
public class EquipmentPTemplateS implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 点位id
     */
    private String pointId;

    /**
     * 料号
     */
    private String stuffNo;

    /**
     * 是否自动加入计划：0=不加入，1=加入
     */
    private Short addPlan;

    /**
     * 寿命/天
     */
    private Short life;

    /**
     * 预警设定/天
     */
    private Short warning;

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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getStuffNo() {
        return stuffNo;
    }

    public void setStuffNo(String stuffNo) {
        this.stuffNo = stuffNo;
    }

    public Short getAddPlan() {
        return addPlan;
    }

    public void setAddPlan(Short addPlan) {
        this.addPlan = addPlan;
    }

    public Short getLife() {
        return life;
    }

    public void setLife(Short life) {
        this.life = life;
    }

    public Short getWarning() {
        return warning;
    }

    public void setWarning(Short warning) {
        this.warning = warning;
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
        return "EquipmentPTemplateS{" +
            "id = " + id +
            ", templateId = " + templateId +
            ", pointId = " + pointId +
            ", stuffNo = " + stuffNo +
            ", addPlan = " + addPlan +
            ", life = " + life +
            ", warning = " + warning +
            ", status = " + status +
            ", remark = " + remark +
            ", createBy = " + createBy +
            ", createTime = " + createTime +
            ", updateBy = " + updateBy +
            ", updateTime = " + updateTime +
        "}";
    }
}
