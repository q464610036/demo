package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养工单节点履历表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentOrderNodeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

    @ApiModelProperty("节点code")
    private String nodeCode;

    @ApiModelProperty("操作人")
    private String operateUserCode;

    @ApiModelProperty("操作人名称")
    private String operateUserName;

    @ApiModelProperty("操作时间")
    private LocalDateTime operateTime;

    @ApiModelProperty("操作状态：1=取消保养，2=通过，9=不通过")
    private String operateStatus;

    @ApiModelProperty("意见")
    private String advice;

    @ApiModelProperty("操作阶段：0=未操作，1=待操作，2=已操作")
    private Short operateStage;

    @ApiModelProperty("序号")
    private Short seqNo;


}
