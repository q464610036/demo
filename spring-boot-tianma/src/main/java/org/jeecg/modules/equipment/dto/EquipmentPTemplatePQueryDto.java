package org.jeecg.modules.equipment.dto;

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
public class EquipmentPTemplatePQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("料号")
    private String stuffNo;

    @ApiModelProperty("排除的物料id")
    private String ignoreStuffId;

}
