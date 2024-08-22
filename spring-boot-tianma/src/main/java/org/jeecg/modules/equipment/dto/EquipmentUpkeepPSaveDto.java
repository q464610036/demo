package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 设备临时保养点位记录表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentUpkeepPSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("残件id")
    private String incompleteId;

    @ApiModelProperty(value = "临时保养id", hidden = true)
    private String tempUpkeepId;

    @ApiModelProperty("设备")
    @NotEmpty(message = "设备不能为空")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("点位id")
    @NotEmpty(message = "点位id不能为空")
    private String pointId;

    @ApiModelProperty("点位名称")
    @NotEmpty(message = "点位名称不能为空")
    private String pointName;

    @ApiModelProperty("数量")
    private Integer num;
}
