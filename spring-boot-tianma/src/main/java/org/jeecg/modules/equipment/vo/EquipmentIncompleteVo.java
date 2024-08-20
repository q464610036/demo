package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备残件表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentIncompleteVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

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

    @ApiModelProperty("保养项目内容")
    private String itemContent;

    @ApiModelProperty("保养标准")
    private String standard;

    @ApiModelProperty("注意事项")
    private String note;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养异常")
    private String upkeepResult;

    @ApiModelProperty("原计划日期")
    private LocalDateTime planTime;

    @ApiModelProperty("实际日期")
    private LocalDateTime realTime;

    @ApiModelProperty("状态：0=待记录，1=审批中，2=执行中，9=未锁定")
    private String lockStatus;

    @ApiModelProperty("状态文案")
    private String lockStatusText;

    @ApiModelProperty("停线类型文案")
    private String stopLineTypeText;

    @ApiModelProperty("周期文案")
    private String cycleText;

    @ApiModelProperty("保养项目id")
    private String itemId;

    @ApiModelProperty("保养结果文案")
    private String upkeepResultText;

    @ApiModelProperty("厂别文案")
    private String factoryNameText;

}
