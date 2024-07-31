package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EquipmentOrderNodeQueryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工单号")
    private String orderNo;

    @ApiModelProperty("工单号列表")
    private List<String> orderNoList;

    @ApiModelProperty("操作阶段：0=未操作，1=待操作，2=已操作")
    private Integer operateStage;

    @ApiModelProperty("操作阶段：0=未操作，1=待操作，2=已操作")
    private List<Integer> operateStageList;
}
