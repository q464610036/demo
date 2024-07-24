package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
public class EquipmentTemplateSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @NotEmpty(groups = {IUpdate.class})
    private String id;

    @ApiModelProperty("模版名称")
    @NotEmpty
    private String templateName;

    @ApiModelProperty("设备类型")
    @NotEmpty
    private String unitType;

    @ApiModelProperty(value = "模版类型：1=by unit,2=by sub unit", hidden = true)
    private String templateType;

    @ApiModelProperty(value = "父节点id", hidden = true)
    private String parentId;

    public interface ICreate{

    }

    public interface IUpdate{

    }
}
