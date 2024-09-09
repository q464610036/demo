package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备异常单审批节点表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_EXC_NODE")
public class EquipmentExcNode extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 工单号
     */
    private String exceptionNo;

    /**
     * 节点code
     */
    private String nodeCode;

    /**
     * 操作人
     */
    private String operateUserCode;

    /**
     * 操作人名称
     */
    private String operateUserName;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 操作状态 0=提交申请，1=待审批，2=通过，9=不通过
     */
    private String operateStatus;

    /**
     * 意见
     */
    private String advice;

    /**
     * 操作阶段：0=未操作，1=待操作，2=已操作
     */
    private Short operateStage;

    /**
     * 序号
     */
    private Short seqNo;
}
