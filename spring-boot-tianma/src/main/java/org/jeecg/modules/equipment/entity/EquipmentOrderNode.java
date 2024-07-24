package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 保养工单节点履历表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@TableName("CT_TMOS_EQUIPMENT_ORDER_NODE")
public class EquipmentOrderNode extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 工单号
     */
    private String orderNo;

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
     * 操作状态：1=取消保养，2=通过，9=不通过
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
