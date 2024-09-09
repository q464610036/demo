package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecg.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 设备异常单用户关联表
 * </p>
 *
 * @author baomidou
 * @since 2024-09-09
 */
@Data
@TableName("CT_TMOS_EQUIPMENT_EXC_USER")
public class EquipmentExcUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 异常单号
     */
    private String exceptionNo;

    /**
     * 用户类型：1=加签
     */
    private Short userType;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 序号
     */
    private Short indexNo;
}
