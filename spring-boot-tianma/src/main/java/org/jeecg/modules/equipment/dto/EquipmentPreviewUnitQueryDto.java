package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
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
public class EquipmentPreviewUnitQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    @NotEmpty
    private String factoryName;

    @ApiModelProperty("设备群组")
    @NotEmpty
    private String area;

    @ApiModelProperty("线体")
    @NotEmpty
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

}
