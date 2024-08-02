package org.jeecg.modules.equipment.vo;

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
public class EquipmentTempUpkeepSaveResultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("借机单号")
    private String borrowNo;
}
