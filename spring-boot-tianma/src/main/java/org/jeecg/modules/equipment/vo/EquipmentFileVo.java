package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author baomidou
 * @since 2024-08-13
 */
@Data
public class EquipmentFileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private String id;

    @ApiModelProperty("数据来源id")
    private String sourceId;

    @ApiModelProperty("来源类型：ORDER_ITEM=保养工单项目")
    private String sourceType;

    @ApiModelProperty("文件类型")
    private String fileType;

    @ApiModelProperty("文件名")
    private String fileName;

    @ApiModelProperty("文件路径")
    private String url;
}
