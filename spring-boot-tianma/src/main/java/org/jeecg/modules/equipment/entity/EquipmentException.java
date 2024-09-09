package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 设备异常单表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_EXCEPTION")
public class EquipmentException extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 异常单号
     */
    private String exceptionNo;

    /**
     * 厂别
     */
    private String factoryName;

    /**
     * 设备群组
     */
    private String area;

    /**
     * 线体
     */
    private String eqpId;

    /**
     * 设备
     */
    private String unitId;

    /**
     * 子设备
     */
    private String subUnitId;

    /**
     * 发生开始时间
     */
    private Date startTime;

    /**
     * 发生结束时间
     */
    private Date endTime;

    /**
     * 异常类型：1=一般异常，2=重大异常
     */
    private String exceptionType;

    /**
     * 原因
     */
    private String reason;

    /**
     * 超标值，单位h
     */
    private BigDecimal excessiveHours;

    /**
     * 异常3阶
     */
    private String exception3Stage;

    /**
     * 异常4阶
     */
    private String exception4Stage;

    /**
     * 临时对策
     */
    private String tempStrategy;

    /**
     * 长期对策
     */
    private String longStrategy;

    /**
     * 是否进行了设备保养或备件更换：0=否，1=是
     */
    private Integer upkeepFlag;

    /**
     * 保养工单号
     */
    private String upkeepOrderNo;

    /**
     * 处理人
     */
    private String processUserCode;

    /**
     * 处理人名称
     */
    private String processUserName;

    /**
     * 审核状态：01=待处理，02=主管审核，03=生产审核，04=加签审核，05=结束，06=驳回
     */
    private String approveStatus;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 主管用户code
     */
    private String leaderUserCode;

    /**
     * 主管用户名称
     */
    private String leaderUserName;

    /**
     * 生产计划用户code
     */
    private String productPlanUserCode;

    /**
     * 生产计划用户名称
     */
    private String productPlanUserName;
}
