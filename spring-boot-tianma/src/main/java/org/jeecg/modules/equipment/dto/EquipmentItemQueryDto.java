package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 保养模版项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentItemQueryDto implements Serializable {

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

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("排除的保养点位id")
    private List<String> ignorePointIds;

    @ApiModelProperty("是否排除残件")
    private Boolean ignoreIncompleteFlag;
}
