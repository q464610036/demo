package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 保养模版表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentTemplateListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模版名称")
    private String templateName;

    @ApiModelProperty("设备类型")
    private String unitType;

    @ApiModelProperty("绑定关系组名称")
    private String groupName;

    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
