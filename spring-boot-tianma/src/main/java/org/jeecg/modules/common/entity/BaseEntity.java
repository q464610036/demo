package org.jeecg.modules.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
    private String remark;
}
