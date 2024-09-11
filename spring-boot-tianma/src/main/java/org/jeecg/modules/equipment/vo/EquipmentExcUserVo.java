package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 设备异常单用户关联表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentExcUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("异常单号")
    private String exceptionNo;

    @ApiModelProperty("用户类型：1=加签")
    private Integer userType;

    @ApiModelProperty("用户code")
    private String userCode;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("序号")
    private Integer indexNo;
}
