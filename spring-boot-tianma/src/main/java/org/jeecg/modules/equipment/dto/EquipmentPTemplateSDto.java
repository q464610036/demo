package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 点位模版点位物料表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentPTemplateSDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("点位id")
    private String pointId;

    @ApiModelProperty("料号")
    private String stuffNo;

    @ApiModelProperty("是否自动加入计划：0=不加入，1=加入")
    private Integer addPlan;

    @ApiModelProperty("寿命/天")
    private Integer life;

    @ApiModelProperty("预警设定/天")
    private Integer warning;
}
