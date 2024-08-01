package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 保养模版项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentPreviewItemVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("模板id")
    private String templateId;

    @ApiModelProperty("保养内容")
    private String itemContent;

    @ApiModelProperty("保养标准")
    private String standard;

    @ApiModelProperty("最小周期(W:周,M:月,2M:双月,Q:季度,H:半年,Y:年,T:临时)")
    private String minCycle;

    @ApiModelProperty("最小周期文案")
    private String minCycleText;

    @ApiModelProperty("子设备")
    private String subUnitIds;
}
