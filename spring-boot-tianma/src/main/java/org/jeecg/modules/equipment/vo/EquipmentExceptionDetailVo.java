package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class EquipmentExceptionDetailVo extends EquipmentExceptionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("节点列表")
    private List<EquipmentExcNodeVo> nodeList;

    @ApiModelProperty("流程节点列表")
    private List<FlowVo> flowList;

    @ApiModelProperty("文件列表")
    private List<EquipmentFileVo> fileList;
}
