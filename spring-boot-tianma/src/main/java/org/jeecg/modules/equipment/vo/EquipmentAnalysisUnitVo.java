package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备异常分析图设备信息
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentAnalysisUnitVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("天列表")
    private List<EquipmentAnalysisDayVo> dayList;

    @ApiModelProperty("设备")
    private String unitId;
}
