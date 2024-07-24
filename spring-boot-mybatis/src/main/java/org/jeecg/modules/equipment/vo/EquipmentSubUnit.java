package org.jeecg.modules.equipment.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养模版设备腔室配置表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_SUB_UNIT")
public class EquipmentSubUnit implements Serializable {

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
