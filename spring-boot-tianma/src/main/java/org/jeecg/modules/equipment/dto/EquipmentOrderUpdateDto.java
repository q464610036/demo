package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 保养工单表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentOrderUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工单id")
    private String id;

    @ApiModelProperty("调整日期")
    private Date adjustTime;
}
