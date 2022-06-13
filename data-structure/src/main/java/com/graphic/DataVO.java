package com.graphic;

import lombok.Builder;
import lombok.Data;

/**
 * @author 陈孟飞
 * @date 2022/6/10
 */
@Data
@Builder
public class DataVO {
    //编码
    private String code;

    //业务类型：project项目，object目标，task任务
    private String businessType;


}
