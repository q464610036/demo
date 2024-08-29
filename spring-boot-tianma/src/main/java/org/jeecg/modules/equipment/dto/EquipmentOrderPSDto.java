package org.jeecg.modules.equipment.dto;

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
public class EquipmentOrderPSDto implements Serializable {

    private  static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private  String id;

    @ApiModelProperty("料号")
    private  String stuffNo;

    @ApiModelProperty("料号数量，没选单品时必填")
    private  Integer num;

    @ApiModelProperty("单品id列表")
    private List<String> singleGoodsIdList;
}
