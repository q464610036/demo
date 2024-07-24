package org.jeecg.modules.equipment.vo;

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
public class EquipmentTemplateDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模版名称")
    private String templateName;

    @ApiModelProperty("设备类型")
    private String unitType;

    @ApiModelProperty("模版类型：1=by unit,2=by sub unit")
    private String templateType;

    @ApiModelProperty("父节点id")
    private String parentId;

    @ApiModelProperty("绑定关系组名称")
    private String groupName;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

}
