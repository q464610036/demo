package org.jeecg.modules.equipment.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备临时保养残件记录表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_UPKEEP_IN")
public class EquipmentUpkeepIn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 临时保养id
     */
    private String tempUpkeepId;

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
     * 保养项目内容
     */
    private String itemContent;

    /**
     * 保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败
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
