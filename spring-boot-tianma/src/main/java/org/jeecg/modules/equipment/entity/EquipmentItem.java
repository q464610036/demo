package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 保养模版项目表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@TableName("CT_TMOS_EQUIPMENT_ITEM")
public class EquipmentItem  extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 保养内容
     */
    private String itemContent;

    /**
     * 最小周期(W:周,M:月,2M:双月,Q:季度,H:半年,Y:年,T:临时)
     */
    private String minCycle;

}
