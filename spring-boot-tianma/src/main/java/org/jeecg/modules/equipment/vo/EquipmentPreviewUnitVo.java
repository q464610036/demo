package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class EquipmentPreviewUnitVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("关系组id")
    private String groupId;

    @ApiModelProperty("保养项目")
    private List<EquipmentPreviewItemVo> itemList;

    @ApiModelProperty("保养点位")
    private List<EquipmentPreviewPointVo> pointList;
}
