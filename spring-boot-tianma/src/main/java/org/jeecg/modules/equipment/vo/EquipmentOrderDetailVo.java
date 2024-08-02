package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 保养工单表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentOrderDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("计划id")
    private String planId;

    @ApiModelProperty("工单号")
    private String orderNo;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("厂别文案")
    private String factoryNameText;

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

    @ApiModelProperty("系统计划日期")
    private LocalDateTime sysPlanTime;

    @ApiModelProperty("调整日期")
    private LocalDateTime adjustTime;

    @ApiModelProperty("预计时长")
    private BigDecimal expectHours;

    @ApiModelProperty("实际开始时间")
    private LocalDateTime realStartTime;

    @ApiModelProperty("实际结束时间")
    private LocalDateTime realEndTime;

    @ApiModelProperty("审核状态：01=待生产审核，02=待设备确认，03=待执行，04=执行中，05=待填报，06=保养完成，07=取消保养，08=保养动作完成")
    private String approveStatus;

    @ApiModelProperty("审核状态文案")
    private String approveStatusText;

    @ApiModelProperty("停线类型文案")
    private String stopLineTypeText;

    @ApiModelProperty("周期文案")
    private String cycleText;

    @ApiModelProperty("节点code")
    private String nodeCode;

    @ApiModelProperty("审批时间")
    private Date approveTime;

    @ApiModelProperty("工单类型：1=保养单，2=取消保养单")
    private String orderType;

    @ApiModelProperty("保养项目列表")
    private List<EquipmentOrderItemVo> itemList;

    @ApiModelProperty("保养设备项目列表")
    private List<EquipmentApproveUnitVo> unitList;

    @ApiModelProperty("节点列表")
    private List<EquipmentOrderNodeVo> nodeList;

    @ApiModelProperty("流程节点列表")
    private List<FlowVo> flowList;
}
