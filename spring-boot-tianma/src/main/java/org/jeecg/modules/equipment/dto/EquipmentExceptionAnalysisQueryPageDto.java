package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseDto;

import java.io.Serializable;
import java.util.Date;

@Data
public class EquipmentExceptionAnalysisQueryPageDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("开始时间")
    private Date startTime;;

    @ApiModelProperty("结束时间")
    private Date endTime;

}
