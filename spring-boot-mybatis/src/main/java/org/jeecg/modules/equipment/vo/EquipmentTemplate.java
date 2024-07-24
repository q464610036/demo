package org.jeecg.modules.equipment.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养模版表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_TEMPLATE")
public class EquipmentTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 模版名称
     */
    private String templateName;

    /**
     * 模版类型：1=by unit,2=by sub unit
     */
    private String templateType;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 关系组id
     */
    private String groupId;

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
