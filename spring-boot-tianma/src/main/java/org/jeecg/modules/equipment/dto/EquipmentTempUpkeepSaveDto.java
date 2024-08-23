package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备临时保养表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentTempUpkeepSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    @NotEmpty
    private String factoryName;

    @ApiModelProperty("设备群组")
    @NotEmpty
    private String area;

    @ApiModelProperty("线体")
    @NotEmpty
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("停线类型：1=整线停线，2=不停线Unit")
    @NotEmpty
    private String stopLineType;

    @ApiModelProperty("临时保养项目列表")
    private List<EquipmentUpkeepInSaveDto> itemList;

    @ApiModelProperty("临时保养点位列表")
    private List<EquipmentUpkeepPSaveDto> pointList;
}
