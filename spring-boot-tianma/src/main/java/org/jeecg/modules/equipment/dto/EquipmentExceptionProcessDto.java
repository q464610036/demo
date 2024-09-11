package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.equipment.vo.EquipmentFileVo;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备异常单表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
public class EquipmentExceptionProcessDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id列表")
    @NotEmpty(message = "id列表不能为空")
    private List<String> idList;

    @ApiModelProperty("异常3阶")
    @NotEmpty(message = "不能为空")
    private String exception3Stage;

    @ApiModelProperty("异常4阶")
    private String exception4Stage;

    @ApiModelProperty("临时对策")
    @NotEmpty(message = "不能为空")
    private String tempStrategy;

    @ApiModelProperty("长期对策")
    private String longStrategy;

    @ApiModelProperty("是否进行了设备保养或备件更换：0=否，1=是")
    @NotEmpty(message = "不能为空")
    private Integer upkeepFlag;

    @ApiModelProperty("处理人")
    private String processUserCode;

    @ApiModelProperty("处理人名称")
    private String processUserName;

    @ApiModelProperty("主管用户code")
    private String leaderUserCode;

    @ApiModelProperty("主管用户名称")
    private String leaderUserName;

    @ApiModelProperty("生产计划用户code")
    private String productPlanUserCode;

    @ApiModelProperty("生产计划用户名称")
    private String productPlanUserName;

    @ApiModelProperty("加签用户列表")
    private List<EquipmentExcUserDto> countersignUserList;

    @ApiModelProperty("旧的加签用户列表")
    private List<EquipmentExcUserDto> oldCountersignUserList;

    @ApiModelProperty("文件列表")
    private List<EquipmentFileVo> fileList;
}
