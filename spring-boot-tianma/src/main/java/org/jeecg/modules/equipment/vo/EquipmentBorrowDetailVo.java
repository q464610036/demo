package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 借机表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-05
 */
@Data
public class EquipmentBorrowDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("借机单号")
    private String borrowNo;

    @ApiModelProperty("借机人")
    private String borrowUserCode;

    @ApiModelProperty("借机人名称")
    private String borrowUserName;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("停线类型：1=Line，2=Unit(不停线)")
    private String stopLineType;

    @ApiModelProperty("借机时间")
    private LocalDateTime borrowTime;

    @ApiModelProperty("计划借机时长")
    private BigDecimal borrowPlanHours;

    @ApiModelProperty("班别：1=白班，2=夜班")
    private String shiftType;

    @ApiModelProperty("借机模式：1=普通借机，2=紧急借机")
    private String borrowMode;

    @ApiModelProperty("借机类型：ENG=工程借机，F_ENG=实验借机，PM=保养借机，EQ_D=宕机保养")
    private String borrowType;

    @ApiModelProperty("借机原因")
    private String borrowReason;

    @ApiModelProperty("主管用户code")
    private String leaderUserCode;

    @ApiModelProperty("生产计划用户code")
    private String productPlanUserCode;

    @ApiModelProperty("主管用户名称")
    private String leaderUserName;

    @ApiModelProperty("生产计划用户名称")
    private String productPlanUserName;

    @ApiModelProperty("子设备")
    private String subUnitId;

    @ApiModelProperty("项目列表")
    private List<EquipmentItemSimpleVo> itemList;

}
