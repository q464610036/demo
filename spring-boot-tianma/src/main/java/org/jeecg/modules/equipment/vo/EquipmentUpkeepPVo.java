package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备临时保养点位记录表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentUpkeepPVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("临时保养id")
    private String tempUpkeepId;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("点位id")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("在机数量")
    private Integer livingNum;

    @ApiModelProperty("在机时长")
    private String livingTime;

    @ApiModelProperty("在机料号（多个，逗号隔开）")
    private String livingStuffNo;

    @ApiModelProperty("在机单品id（多个，逗号隔开）")
    private String livingSingleGoodsId;

    @ApiModelProperty("在机单品列表")
    private List<SingleGoodsLivingVo> livingSingleGoodsList;
}
