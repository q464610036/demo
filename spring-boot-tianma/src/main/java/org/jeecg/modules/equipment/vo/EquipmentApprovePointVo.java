package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 保养工单点位列表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentApprovePointVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitIds;

    @ApiModelProperty("点位id")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("最小周期")
    private String minCycle;

    @ApiModelProperty("寿命/天")
    private Integer life;

    @ApiModelProperty("预计更换时间")
    private Date expectReplaceTime;

    @ApiModelProperty("最小周期文案")
    private String minCycleText;

    @ApiModelProperty("物料类别")
    private String stuffType;

    @ApiModelProperty("物料描述")
    private String stuffDesc;

    @ApiModelProperty("在机数量")
    private Integer onlineNum;

    @ApiModelProperty("在机料号")
    private String onlineStuffNo;

    @ApiModelProperty("在机单品id")
    private String onlineSingleGoodsId;

    @ApiModelProperty("在机时长")
    private Integer onlineHours;
}
