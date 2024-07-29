package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 保养工单项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@TableName("CT_TMOS_EQUIPMENT_ORDER_ITEM")
public class EquipmentOrderItem extends BaseEntity implements Serializable {

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
     * 保养工单设备主键
     */
    private String orderUnitPkId;

    /**
     * 项目内容
     */
    private String itemContent;

    /**
     * 最小周期(W:周,M:月,2M:双月,Q:季度,H:半年,Y:年,T:临时)
     */
    private String minCycle;

    /**
     * 设备
     */
    private String unitId;

    /**
     * 子设备
     */
    private String subUnitId;

    /**
     * 保养结果：0=待保养，1=完成，2=未保养，3=残件，9=保养失败
     */
    private String upkeepResult;

}
