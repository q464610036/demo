package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 点位残件表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentIncompletePListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

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

    @ApiModelProperty("周期")
    private String cycle;

    @ApiModelProperty("点位id")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，4=寿命上限，5=FDC，9=保养失败")
    private String upkeepResult;

    @ApiModelProperty("原计划日期")
    private Date planTime;

    @ApiModelProperty("实际日期")
    private Date realTime;

    @ApiModelProperty("锁定状态：0=待记录，1=审批中，2=执行中")
    private String lockStatus;

    @ApiModelProperty("周期文案")
    private String cycleText;

    @ApiModelProperty("保养结果文案")
    private String upkeepResultText;

    @ApiModelProperty("保养结果描述")
    private String upkeepResultDesc;

    @ApiModelProperty("状态文案")
    private String lockStatusText;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("厂别文案")
    private String factoryNameText;

    @ApiModelProperty("停线类型：1=整线停线，2=不停线Unit")
    private String stopLineType;

    @ApiModelProperty("停线类型文案")
    private String stopLineTypeText;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("借机单号")
    private String borrowNo;

    @ApiModelProperty("借机单id")
    private String borrowId;

    @ApiModelProperty("保养工单id")
    private String orderId;

    @ApiModelProperty("物料类别")
    private String stuffType;

    @ApiModelProperty("物料描述")
    private String stuffDesc;

    @ApiModelProperty("寿命/天")
    private Integer life;

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
