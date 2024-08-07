package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
public class EquipmentIncompleteSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    @NotEmpty
    private String factoryName;

    @ApiModelProperty("设备群组")
    @NotEmpty
    private String area;

    @ApiModelProperty("线体")
    @NotEmpty
    private String eqpId;

    @ApiModelProperty("设备")
    @NotEmpty
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("停线类型：1=整线停线，2=不停线Unit")
    @NotEmpty
    private String stopLineType;

    @ApiModelProperty("周期(W:周,M:月,D:双月,Q:季度,H:半年,Y:年,T:临时,N:宕机)")
    @NotEmpty
    private String cycle;

    @ApiModelProperty("保养项目内容")
    @NotEmpty
    private String itemContent;

    @ApiModelProperty("保养标准")
    @NotEmpty
    private String standard;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败")
    @NotEmpty
    private String upkeepResult;

    @ApiModelProperty("原计划日期")
    private Date planTime;

    @ApiModelProperty("实际日期")
    private Date realTime;

    @ApiModelProperty("保养项目id")
    @NotEmpty
    private String itemId;

    @ApiModelProperty("备注")
    private String remark;


    public interface ICreate{

    }

    public interface IUpdate{

    }
}
