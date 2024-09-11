package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseDto;

import java.io.Serializable;

@Data
public class EquipmentExceptionProcessResultQueryPageDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("关键字")
    private String keyword;

    @ApiModelProperty("仅本部门")
    private Boolean onlyDepartment;
}
