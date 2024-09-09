package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 保养模版项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户code")
    private String userCode;

    @ApiModelProperty("用户名称")
    private String userName;
}
