package org.jeecg.modules.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.equipment.dto.EquipmentOrderApproveDto;
import org.jeecg.modules.equipment.dto.EquipmentSaveUpkeepResultDto;
import org.jeecg.modules.equipment.entity.EquipmentOrder;

/**
 * <p>
 * 保养工单表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
public interface IEquipmentOrderService extends IService<EquipmentOrder> {

    void approve(EquipmentOrderApproveDto dto);

    boolean updateById(EquipmentOrder entity);

    void submitResult(EquipmentSaveUpkeepResultDto dto);

    void saveResult(EquipmentSaveUpkeepResultDto dto);

}
