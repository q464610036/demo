package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 在机信息vo
 * </p>
 *
 * @author baomidou
 * @since 2024-08-13
 */
@Data
public class PointLivingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("在机数量")
    private Integer livingNum;

    @ApiModelProperty("在机时长")
    private String livingTime;

    @ApiModelProperty("在机料号（多个，逗号隔开）")
    private String livingStuffNo;

    @ApiModelProperty("在机单品id（多个，逗号隔开）")
    private String livingSingleGoodsId;

    @ApiModelProperty("单品列表")
    private List<SingleGoodsLivingVo> singleGoodsList;
}
