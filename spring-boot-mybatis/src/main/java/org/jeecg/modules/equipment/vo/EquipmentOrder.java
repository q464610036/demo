package org.jeecg.modules.equipment.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养工单表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_ORDER")
public class EquipmentOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 计划id
     */
    private String planId;

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
     * 停线类型：1=整线停线，2=不停线Unit
     */
    private String stopLineType;

    /**
     * 周期
     */
    private String cycle;

    /**
     * 系统计划日期
     */
    private LocalDateTime sysPlanTime;

    /**
     * 调整日期
     */
    private LocalDateTime adjustTime;

    /**
     * 预计时长
     */
    private BigDecimal expectHours;

    /**
     * 实际开始时间
     */
    private LocalDateTime realStartTime;

    /**
     * 实际结束时间
     */
    private LocalDateTime realEndTime;

    /**
     * 审核状态：01=待生产审核，02=待设备确认，03=待执行，04=执行中，05=待填报，06=保养完成，07=取消保养
     */
    private String approveStatus;

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
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

}
