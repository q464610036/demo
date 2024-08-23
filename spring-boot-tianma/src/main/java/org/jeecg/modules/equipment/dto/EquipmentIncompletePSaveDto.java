package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 点位残件表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@Data
public class EquipmentIncompletePSaveDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    @NotEmpty(message = "厂别不能为空")
    private String factoryName;

    @ApiModelProperty("设备群组")
    @NotEmpty(message = "设备群组不能为空")
    private String area;

    @ApiModelProperty("线体")
    @NotEmpty(message = "线体不能为空")
    private String eqpId;

    @ApiModelProperty("设备")
    @NotEmpty(message = "设备不能为空")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("周期")
    @NotEmpty(message = "周期不能为空")
    private String cycle;

    @ApiModelProperty("点位id")
    @NotEmpty(message = "点位id不能为空")
    private String pointId;

    @ApiModelProperty("点位名称")
    @NotEmpty(message = "点位名称不能为空")
    private String pointName;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，4=寿命上限，5=FDC，9=保养失败")
    @NotEmpty(message = "保养结果不能为空")
    private String upkeepResult;

    @ApiModelProperty("原计划日期")
    private Date planTime;

    @ApiModelProperty("实际日期")
    private Date realTime;
}
