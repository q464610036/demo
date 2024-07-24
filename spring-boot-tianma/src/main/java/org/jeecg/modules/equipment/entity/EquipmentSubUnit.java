package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 保养模版设备腔室配置表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@TableName("CT_TMOS_EQUIPMENT_SUB_UNIT")
public class EquipmentSubUnit extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 模版id
     */
    private String templateId;

    /**
     * 设备unit组别
     */
    private String subUnitGroup;

}
