package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 设备临时保养残件记录表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentUpkeepInSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("残件id")
    private String incompleteId;

    @ApiModelProperty(value = "临时保养id", hidden = true)
    private String tempUpkeepId;

    @ApiModelProperty("保养项目内容")
    private String itemContent;

    @ApiModelProperty("保养标准")
    private String standard;

    @ApiModelProperty("注意事项")
    private String note;

    @ApiModelProperty("保养项目id")
    private String itemId;

    @ApiModelProperty("设备")
    @NotEmpty
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;
}
