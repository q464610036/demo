package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
public class DeleteDto implements Serializable {

    @ApiModelProperty("主键id")
    @NotEmpty
    private List<String> idList;
}
