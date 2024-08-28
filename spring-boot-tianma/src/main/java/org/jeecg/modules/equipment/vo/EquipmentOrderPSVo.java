package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 保养工单点位料号列表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-27
 */
@Data
public class EquipmentOrderPSVo implements Serializable {

    private  static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private  String id;

    @ApiModelProperty("订单点位id")
    private  String orderPointId;

    @ApiModelProperty("物料数量")
    private  Integer num;

    @ApiModelProperty("物料类别")
    private String stuffType;

    @ApiModelProperty("物料描述")
    private String stuffDesc;

    @ApiModelProperty("料号")
    private  String stuffNo;

    @ApiModelProperty("单品id（多个，逗号隔开）")
    private  String singleGoodsIds;

    @ApiModelProperty("单品id列表")
    private List<String> singleGoodsIdList;
}
