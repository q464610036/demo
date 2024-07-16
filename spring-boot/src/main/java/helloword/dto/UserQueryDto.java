package helloword.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserQueryDto {
    @ApiModelProperty("主键")
    @NotNull
    private Integer id;

    @ApiModelProperty("名称")
    @NotEmpty
    private String name;
}
