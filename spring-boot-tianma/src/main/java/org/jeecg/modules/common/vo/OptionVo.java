package org.jeecg.modules.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class OptionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("文案")
    private String label;
}
