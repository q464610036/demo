package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class EquipmentIncompletePVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("工单号")
    private String orderNo;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("周期")
    private String cycle;

    @ApiModelProperty("点位id")
    private String pointId;

    @ApiModelProperty("点位名称")
    private String pointName;

    @ApiModelProperty("保养结果：0=待保养，1=完成，2=未保养，3=残件，4=寿命上限，5=FDC，9=保养失败")
    private String upkeepResult;

    @ApiModelProperty("原计划日期")
    private Date planTime;

    @ApiModelProperty("实际日期")
    private Date realTime;

    @ApiModelProperty("锁定状态：0=待记录，1=审批中，2=执行中")
    private String lockStatus;
}
