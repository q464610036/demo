package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class EquipmentPointUpkeepResultDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("点位id")
    @NotEmpty(message = "点位id不能为空")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("设备")
    @NotEmpty(message = "设备不能为空")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("最小周期(W:周,M:月,D:双月,Q:季度,H:半年,Y:年,T:临时,N:宕机)")
    private String minCycle;

    @ApiModelProperty("寿命/天")
    private Integer life;

    @ApiModelProperty("预计更换时间")
    private Date expectReplaceTime;

    @ApiModelProperty("数量")
    @NotNull(message = "设备不能为空")
    private Integer num;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养异常")
    private String upkeepResult;

    @ApiModelProperty("保养人code")
    @NotEmpty(message = "保养人不能为空")
    private String upkeepUserCode;

    @ApiModelProperty("保养人名称")
    @NotEmpty(message = "保养人不能为空")
    private String upkeepUserName;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("作业标准")
    private String standard;

    @ApiModelProperty("物料列表")
    private List<EquipmentOrderPSDto> stuffList;

    @ApiModelProperty("旧的物料列表")
    private List<EquipmentOrderPSDto> oldStuffList;
}
