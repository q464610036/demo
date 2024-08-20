package org.jeecg.modules.equipment.vo;

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
public class EquipmentPTemplateSVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模板id")
    private String templateId;

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

    @ApiModelProperty("物料类别")
    private String stuffType;

    @ApiModelProperty("物料描述")
    private String stuffDesc;

}
