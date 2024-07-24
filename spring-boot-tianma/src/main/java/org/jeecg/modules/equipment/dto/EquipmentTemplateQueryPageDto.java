package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 保养模版表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentTemplateQueryPageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("模版名称")
    private String templateName;

    @ApiModelProperty("设备类型")
    private String unitType;

    @ApiModelProperty(value = "父节点id", hidden = true)
    private String parentId;
}
