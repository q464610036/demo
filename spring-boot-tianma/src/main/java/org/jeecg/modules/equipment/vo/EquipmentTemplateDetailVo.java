package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class EquipmentTemplateDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模版名称")
    private String templateName;

    @ApiModelProperty("版本号")
    private String rev;

    @ApiModelProperty("设备类型")
    private String unitType;

    @ApiModelProperty("模版类型：1=by unit,2=by sub unit")
    private String templateType;

    @ApiModelProperty("父节点id")
    private String parentId;

    @ApiModelProperty("关系组id列表")
    private List<String> groupIdList;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "保养项目列表")
    private List<EquipmentItemVo> itemList;

    @ApiModelProperty("子模版")
    private List<EquipmentTemplateChildVo> childTemplate;
}
