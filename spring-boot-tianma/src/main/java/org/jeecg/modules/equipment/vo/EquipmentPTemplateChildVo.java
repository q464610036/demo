package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 点位模版表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentPTemplateChildVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("父节点id")
    private String parentId;

    @ApiModelProperty("子设备关系组")
    private String subUnitGroup;

    @ApiModelProperty(value = "子设备类型列表")
    private List<String> subUnitTypeList;

    @ApiModelProperty(value = "保养点位列表")
    private List<EquipmentPTemplatePVo> pointList;


}
