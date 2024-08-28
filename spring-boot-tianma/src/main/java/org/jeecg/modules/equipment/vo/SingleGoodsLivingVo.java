package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 在机单品信息vo
 * </p>
 *
 * @author baomidou
 * @since 2024-08-13
 */
@Data
public class SingleGoodsLivingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("在机料号")
    private String livingStuffNo;

    @ApiModelProperty("在机单品id")
    private String livingSingleGoodsId;

    @ApiModelProperty("在机预计更换时间")
    private Date livingExpectReplaceTime;
}
