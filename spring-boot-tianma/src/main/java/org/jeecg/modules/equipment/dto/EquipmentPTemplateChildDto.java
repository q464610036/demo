package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 点位子模版
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentPTemplateChildDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty(value = "父节点id", hidden = true)
    private String parentId;

    @ApiModelProperty(value = "设备id")
    private String unitId;

    @ApiModelProperty(value = "子设备类型列表")
    private List<String> subUnitTypeList;

    @ApiModelProperty(value = "保养点位列表")
    private List<EquipmentPTemplatePDto> pointList;
}
