package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 保养工单项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentSaveUpkeepResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工单id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

//    @ApiModelProperty("厂别")
//    @NotEmpty
//    private String factoryName;
//
//    @ApiModelProperty("设备群组")
//    @NotEmpty
//    private String area;
//
//    @ApiModelProperty("线体")
//    @NotEmpty
//    private String eqpId;

    @ApiModelProperty("保养结果项目列表")
    @Valid
    private List<EquipmentItemUpkeepResultDto> itemList;

    @ApiModelProperty("保养结果点位列表")
    @Valid
    private List<EquipmentPointUpkeepResultDto> pointList;

}
