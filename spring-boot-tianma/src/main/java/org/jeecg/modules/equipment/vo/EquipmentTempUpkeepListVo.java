package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 设备临时保养表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentTempUpkeepListVo implements Serializable {

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

    @ApiModelProperty("周期(W:周,M:月,D:双月,Q:季度,H:半年,Y:年,T:临时,N:宕机)")
    private String cycle;

    @ApiModelProperty("停线类型文案")
    private String stopLineTypeText;

    @ApiModelProperty("周期文案")
    private String cycleText;

    @ApiModelProperty("厂别文案")
    private String factoryNameText;

    @ApiModelProperty("计划借机日期")
    private Date borrowTime;

    @ApiModelProperty("计划借机时长")
    private BigDecimal borrowPlanHours;

    @ApiModelProperty("借机工单状态")
    private String approveStatus;

    @ApiModelProperty("借机工单状态文案")
    private String approveStatusText;

    @ApiModelProperty("借机人/创建人")
    private String borrowUserName;

    @ApiModelProperty("创建日期")
    private Date createTime;
}
