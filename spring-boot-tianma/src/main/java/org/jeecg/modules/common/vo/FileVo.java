package org.jeecg.modules.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文件上传返回信息
 * </p>
 *
 * @author baomidou
 * @since 2024-08-13
 */
@Data
public class FileVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件类型")
    private String fileType;

    @ApiModelProperty("文件名")
    private String fileName;

    @ApiModelProperty("文件唯一key")
    private String key;
}
