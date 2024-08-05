package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("保养工单号")
    private String orderNo;

    @ApiModelProperty("借机单号")
    private String borrowNo;

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

    @ApiModelProperty("停线类型：1=整线停线，2=不停线Unit")
    private String stopLineType;

    @ApiModelProperty("周期(W:周,M:月,2M:双月,Q:季度,H:半年,Y:年,T:临时)")
    private String cycle;

//    @ApiModelProperty("计划日期")
//    private Date planTime;
//
//    @ApiModelProperty("实际日期")
//    private Date realTime;
//
//    @ApiModelProperty("预计时长")
//    private BigDecimal expectHours;
//
//    @ApiModelProperty("实际时长")
//    private BigDecimal realHours;

    @ApiModelProperty("临时保养项目列表")
    List<EquipmentUpkeepInSaveDto> itemList;
}
