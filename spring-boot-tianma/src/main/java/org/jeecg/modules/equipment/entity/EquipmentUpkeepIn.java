package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

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
@TableName("CT_TMOS_EQUIPMENT_UPKEEP_IN")
public class EquipmentUpkeepIn  extends BaseEntity implements Serializable {

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
     * 周期(W:周,M:月,2M:双月,Q:季度,H:半年,Y:年,T:临时)
     */
    private String cycle;

    /**
     * 项目id
     */
    private String itemId;

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

}
