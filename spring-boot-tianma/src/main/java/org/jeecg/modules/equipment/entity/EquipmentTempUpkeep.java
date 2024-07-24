package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 设备临时保养表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@TableName("CT_TMOS_EQUIPMENT_TEMP_UPKEEP")
public class EquipmentTempUpkeep extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 保养工单号
     */
    private String orderNo;

    /**
     * 借机单号
     */
    private String borrowNo;

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

}
