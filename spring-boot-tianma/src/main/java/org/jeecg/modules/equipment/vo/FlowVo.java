package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 流程vo
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class FlowVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流程节点文案")
    private String text;

    @ApiModelProperty("高亮")
    private Boolean highlight;
}
