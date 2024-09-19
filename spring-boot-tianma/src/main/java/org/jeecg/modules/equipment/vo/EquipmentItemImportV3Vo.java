package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 保养工单项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentItemImportV3Vo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("版本号")
    private String rev;

    @ApiModelProperty("项目列表")
    private List<String> itemList;

}
