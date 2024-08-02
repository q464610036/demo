package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
public class EquipmentIncompleteSaveListDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("残件对象列表")
    @NotEmpty
    private List<EquipmentIncompleteSaveDto> list;


}
