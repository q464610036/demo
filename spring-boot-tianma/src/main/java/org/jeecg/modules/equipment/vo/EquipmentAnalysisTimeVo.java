package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 设备异常单表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentAnalysisTimeVo extends EquipmentExceptionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("是否已处理")
    private Boolean processFlag;
}