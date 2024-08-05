package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("临时保养id")
    private String tempUpkeepId;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("保养项目内容")
    private String itemContent;

    @ApiModelProperty("保养标准")
    private String standard;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败")
    private String upkeepResult;

    @ApiModelProperty("原计划日期")
    private LocalDateTime planTime;

    @ApiModelProperty("实际日期")
    private LocalDateTime realTime;

    @ApiModelProperty("保养项目id")
    private String itemId;

}
