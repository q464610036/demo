package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 设备残件表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Data
public class EquipmentIncompleteQueryPageDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty("锁定状态：0=待记录，1=审批中，2=执行中，9=未锁定")
    private String lockStatus;

    @ApiModelProperty("设备列表")
    private List<String> unitIdList;

    @ApiModelProperty("子设备列表")
    private List<String> subUnitIdList;

    @ApiModelProperty(value = "是否查询在机信息", notes = "默认不查询，为true才查询")
    private Boolean queryLiving;
}
