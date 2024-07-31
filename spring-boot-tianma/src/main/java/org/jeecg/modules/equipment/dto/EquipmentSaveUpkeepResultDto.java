package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 保养工单项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentSaveUpkeepResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("保养结果列表")
    private List<EquipmentItemUpkeepResultDto> itemList;


}
