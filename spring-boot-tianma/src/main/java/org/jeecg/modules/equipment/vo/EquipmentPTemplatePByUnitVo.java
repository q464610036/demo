package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 点位模版点位表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentPTemplatePByUnitVo implements Serializable {

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

    @ApiModelProperty("最小周期文案")
    private String cycleText;

    @ApiModelProperty("物料列表")
    private List<EquipmentPTemplateSVo> stuffList;

    @ApiModelProperty("在机数量")
    private Integer livingNum;

    @ApiModelProperty("在机料号")
    private String livingStuffNo;

    @ApiModelProperty("在机单品id")
    private String livingSingleGoodsId;

    @ApiModelProperty("在机时长")
    private String livingTime;

    @ApiModelProperty("在机预计更换时间")
    private Date livingExpectReplaceTime;

    @ApiModelProperty("寿命/天")
    private Integer life;
}
