package org.jeecg.modules.equipment.service;

import org.jeecg.modules.equipment.entity.EquipmentOrderNode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保养工单节点履历表 服务类
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
public interface IEquipmentOrderNodeService extends IService<EquipmentOrderNode> {

    boolean updateById(EquipmentOrderNode entity);
}
