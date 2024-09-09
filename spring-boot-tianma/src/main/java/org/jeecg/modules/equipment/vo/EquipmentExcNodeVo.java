package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EquipmentExcNodeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("工单号")
    private String exceptionNo;

    @ApiModelProperty("节点code")
    private String nodeCode;

    @ApiModelProperty("操作人")
    private String operateUserCode;

    @ApiModelProperty("操作人名称")
    private String operateUserName;

    @ApiModelProperty("操作时间")
    private Date operateTime;

    @ApiModelProperty("操作状态 0=提交申请，1=待审批，2=通过，9=不通过")
    private String operateStatus;

    @ApiModelProperty("意见")
    private String advice;

    @ApiModelProperty("操作阶段：0=未操作，1=待操作，2=已操作")
    private Integer operateStage;

    @ApiModelProperty("序号")
    private Integer seqNo;

    @ApiModelProperty("操作状态文案")
    private String operateStatusText;

    @ApiModelProperty("节点文案")
    private String nodeCodeText;

    @ApiModelProperty("节点类型:0=非审批节点，1=审批节点")
    private String nodeType;
}
