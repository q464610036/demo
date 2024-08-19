package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 点位模版表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentPTemplateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模版名称")
    private String templateName;

    @ApiModelProperty("模版类型：1=by unit,2=by sub unit")
    private String templateType;

    @ApiModelProperty("设备类型")
    private String unitType;

    @ApiModelProperty("父节点id")
    private String parentId;

    @ApiModelProperty("子设备关系组")
    private String subUnitGroup;

    @ApiModelProperty("创建人名称")
    private String createUserName;

    @ApiModelProperty("设备")
    private String unitId;

}
