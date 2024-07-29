package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseDto;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EquipmentOrderQueryPageDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("工单状态：01=待生产审核，02=待设备确认，03=待执行，04=执行中，05=待填报，06=保养完成，07=取消保养")
    private String approveStatus;

    @ApiModelProperty("开始计划日期")
    private LocalDateTime startSysPlanTime;

    @ApiModelProperty("结束计划日期")
    private LocalDateTime endSysPlanTime;

    @ApiModelProperty("工单号")
    private String orderNo;
}
