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
public class EquipmentPTemplateListVo implements Serializable {

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

    @ApiModelProperty("关系组名称（多个用逗号隔开）")
    private String groupName;

    @ApiModelProperty("点位（多个用逗号隔开）")
    private String pointName;

    @ApiModelProperty("点位列表")
    private List<EquipmentPTemplatePVo> pointList;

    @ApiModelProperty("创建人名称")
    private String createUserName;

    @ApiModelProperty("创建时间")
    private String createTime;


}
