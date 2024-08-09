package org.jeecg.modules.equipment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.common.vo.OptionVo;

import java.io.Serializable;
import java.util.List;
@Data
public class EquipmentOrderBaseDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("审核状态")
    private List<OptionVo> approveStatus;


}
