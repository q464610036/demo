package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备异常分析图天信息
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentAnalysisDayVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("时间段列表")
    private List<EquipmentAnalysisTimeVo> timeList;

    @ApiModelProperty("日期")
    private String day;
}
