package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 点位模版点位表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentPTemplatePVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模板id")
    private String templateId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("最小周期")
    private String cycle;

    @ApiModelProperty("作业标准")
    private String standard;

}
