package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 保养工单表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentOrderApproveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工单id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

    @ApiModelProperty("操作状态：1=取消保养，2=通过，9=不通过")
    @NotEmpty
    private String operateStatus;

    @ApiModelProperty("意见")
    private String advice;

}
