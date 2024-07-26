package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 保养模版表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@TableName("CT_TMOS_EQUIPMENT_TEMPLATE")
public class EquipmentTemplate extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;


    private String templateName;

    /**
     * 设备类型
     */
    private String unitType;

    /**
     * 模版类型：1=by unit,2=by sub unit
     */
    private String templateType;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    private String unitId;

}
