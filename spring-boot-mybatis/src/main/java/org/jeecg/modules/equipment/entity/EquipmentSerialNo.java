package org.jeecg.modules.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 设备序列号记录表
 * </p>
 *
 * @author baomidou
 * @since 2024-07-07
 */
@TableName("CT_TMOS_EQUIPMENT_SERIAL_NO")
public class EquipmentSerialNo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建日期
     */
    private String createDate;

    /**
     * 业务类型：borrow=借机
     */
    private String businessType;

    /**
     * 序列号
     */
    private Integer serialNo;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "EquipmentSerialNo{" +
            "createDate = " + createDate +
            ", businessType = " + businessType +
            ", serialNo = " + serialNo +
        "}";
    }
}
