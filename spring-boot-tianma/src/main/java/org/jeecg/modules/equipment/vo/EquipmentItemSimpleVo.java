package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 保养模版项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentItemSimpleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("保养id")
    private String itemId;
    
    @ApiModelProperty("保养内容")
    private String itemContent;

    @ApiModelProperty("保养标准")
    private String standard;
}
