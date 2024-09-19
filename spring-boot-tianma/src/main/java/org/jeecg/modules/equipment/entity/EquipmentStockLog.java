package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 部门库存操作日志表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-19
 */
@TableName("CT_TMOS_EQUIPMENT_STOCK_LOG")
public class EquipmentStockLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 操作节点
     */
    private String operationNode;

    /**
     * 料号
     */
    private String stuffNo;

    /**
     * 部门总库存
     */
    private Integer stockNum;

    /**
     * 良品库存
     */
    private Integer commonStockNum;

    /**
     * 在机库存
     */
    private Integer upStockNum;

    /**
     * 下机库存
     */
    private Integer downStockNum;

    /**
     * 监控上机库存
     */
    private Integer monitUpStockNum;

    /**
     * 监控下机库存
     */
    private Integer monitDownStockNum;

    /**
     * 设备监控上机id，多个用逗号隔开
     */
    private String monitUpStockIds;

    /**
     * 设备监控下机id，多个用逗号隔开
     */
    private String monitDownStockIds;

    /**
     * 异常代码 0-正常 1-实际上下机器数与库存不符 2-负数库存 3-库存总数不一致
     */
    private String errorCode;

}
