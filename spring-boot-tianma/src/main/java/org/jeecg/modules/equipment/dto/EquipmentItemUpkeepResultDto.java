package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 保养工单项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentItemUpkeepResultDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("工单保养项目id")
    private String id;

    @ApiModelProperty("模板保养项目id")
    private String itemId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养异常")
    private String upkeepResult;

    @ApiModelProperty("保养人")
    private String upkeepUserCode;

    @ApiModelProperty("保养人名称")
    private String upkeepUserName;

    @ApiModelProperty("备注")
    private String remark;
}
