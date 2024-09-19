package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

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

    @ApiModelProperty("关系组id列表")
    private List<String> groupIdList;

    @ApiModelProperty("模版名称")
    @NotEmpty
    private String templateName;

    @ApiModelProperty("版本号")
    private String rev;

    @ApiModelProperty("模版类型：1=by unit,2=by sub unit")
    @NotEmpty
    private String templateType;

    @ApiModelProperty("设备类型")
    @NotEmpty
    private String unitType;

    @ApiModelProperty(value = "保养项目列表")
    private List<EquipmentItemDto> itemList;

    @ApiModelProperty(value = "旧的保养项目列表")
    private List<EquipmentItemDto> oldItemList;

    @ApiModelProperty("子模版")
    private List<EquipmentTemplateChildDto> childTemplate;

    @ApiModelProperty("旧的子模版")
    private List<EquipmentTemplateChildDto> oldChildTemplate;


    public interface ICreate{

    }

    public interface IUpdate{

    }
}
