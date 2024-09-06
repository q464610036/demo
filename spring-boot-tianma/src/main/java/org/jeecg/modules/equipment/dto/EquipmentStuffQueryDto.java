package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EquipmentStuffQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("料号")
    private String stuffNo;

    @ApiModelProperty("排除的单品id")
    private List<String> ignoreSingleGoodsIdList;
}
