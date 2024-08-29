package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 保养工单点位列表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentOrderPVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败")
    private String upkeepResult;

    @ApiModelProperty("保养人code")
    private String upkeepUserCode;

    @ApiModelProperty("保养人名称")
    private String upkeepUserName;

    @ApiModelProperty("点位id")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("最小周期")
    private String minCycle;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("预计更换时间")
    private Date expectReplaceTime;

    @ApiModelProperty("最小周期文案")
    private String minCycleText;

    @ApiModelProperty("保养结果文案")
    private String upkeepResultText;

    @ApiModelProperty("物料列表")
    private List<EquipmentOrderPSVo> stuffList;

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
