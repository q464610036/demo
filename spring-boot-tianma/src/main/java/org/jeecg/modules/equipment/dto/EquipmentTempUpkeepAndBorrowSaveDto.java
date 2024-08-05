package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 设备临时保养表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentTempUpkeepAndBorrowSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("临时保养保存对象")
    private EquipmentTempUpkeepSaveDto empUpkeep;

    @ApiModelProperty("借机单保存对象")
    private EquipmentBorrowSaveDto borrow;
}
