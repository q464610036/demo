package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 设备异常单表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentExceptionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("异常单号")
    private String exceptionNo;

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

    @ApiModelProperty("发生开始时间")
    private Date startTime;

    @ApiModelProperty("发生结束时间")
    private Date endTime;

    @ApiModelProperty("异常类型：1=一般异常，2=重大异常")
    private String exceptionType;

    @ApiModelProperty("异常类型文案")
    private String exceptionTypeText;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("超标值，单位h")
    private BigDecimal excessiveHours;

    @ApiModelProperty("异常3阶")
    private String exception3Stage;

    @ApiModelProperty("异常4阶")
    private String exception4Stage;

    @ApiModelProperty("临时对策")
    private String tempStrategy;

    @ApiModelProperty("长期对策")
    private String longStrategy;

    @ApiModelProperty("是否进行了设备保养或备件更换：0=否，1=是")
    private Integer upkeepFlag;

    @ApiModelProperty("保养工单号")
    private String upkeepOrderNo;

    @ApiModelProperty("处理人")
    private String processUserCode;

    @ApiModelProperty("处理人名称")
    private String processUserName;

    @ApiModelProperty("审核状态：01=待处理，02=主管审核，03=生产审核，04=加签审核，05=结束，06=驳回")
    private String approveStatus;

    @ApiModelProperty("审核状态文案")
    private String approveStatusText;

    @ApiModelProperty("审批时间")
    private Date approveTime;

    @ApiModelProperty("主管用户code")
    private String leaderUserCode;

    @ApiModelProperty("主管用户名称")
    private String leaderUserName;

    @ApiModelProperty("生产计划用户code")
    private String productPlanUserCode;

    @ApiModelProperty("生产计划用户名称")
    private String productPlanUserName;

    @ApiModelProperty("持续时间")
    private String duration;
}
