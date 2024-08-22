package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ValidatePointNameDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("点位主键id")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;
}
