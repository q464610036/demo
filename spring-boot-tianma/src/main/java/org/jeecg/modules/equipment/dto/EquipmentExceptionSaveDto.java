package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备异常单表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentExceptionSaveDto implements Serializable {

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

    @ApiModelProperty("发生开始时间")
    @NotNull(message = "发生开始时间不能为空")
    private Date startTime;

    @ApiModelProperty("发生结束时间")
    @NotNull(message = "发生结束时间不能为空")
    private Date endTime;

    @ApiModelProperty("异常类型：1=一般异常，2=重大异常")
    @NotEmpty(message = "异常类型不能为空")
    private String exceptionType;

    @ApiModelProperty("原因")
    @NotEmpty(message = "原因不能为空")
    private String reason;

}
