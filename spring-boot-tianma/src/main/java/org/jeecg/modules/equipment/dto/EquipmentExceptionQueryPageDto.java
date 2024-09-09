package org.jeecg.modules.equipment.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseDto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class EquipmentExceptionQueryPageDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("厂别")
    private String factoryName;

    @ApiModelProperty("设备群组")
    private String area;

    @ApiModelProperty("线体")
    private String eqpId;

    @ApiModelProperty("设备")
    private String unitId;

    @ApiModelProperty("异常类型：1=一般异常，2=重大异常")
    private String exceptionType;

    @ApiModelProperty("发生开始时间开始")
    private Date startStartTime;;

    @ApiModelProperty("发生开始时间结束")
    private Date endStartTime;

    @ApiModelProperty("审核状态：01=待处理，02=主管审核，03=生产审核，04=加签审核，05=结束，06=驳回")
    private String approveStatus;

    @ApiModelProperty("审核状态：01=待处理，02=主管审核，03=生产审核，04=加签审核，05=结束，06=驳回")
    private List<String> approveStatusList;

    @ApiModelProperty("异常单号")
    private String exceptionNo;
}
