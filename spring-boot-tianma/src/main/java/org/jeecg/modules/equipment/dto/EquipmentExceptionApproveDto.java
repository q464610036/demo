package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class EquipmentExceptionApproveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工单id")
    private String id;

    @ApiModelProperty("异常单号")
    private String exceptionNo;

    @ApiModelProperty("操作状态 0=提交申请，1=待审批，2=通过，9=不通过")
    @NotEmpty
    private String operateStatus;

    @ApiModelProperty("意见")
    private String advice;

}
