package org.jeecg.modules.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseDto {
    @ApiModelProperty("当前页")
    private Integer pageNo;
    @ApiModelProperty("每页显示记录数")
    private Integer pageSize;
}
