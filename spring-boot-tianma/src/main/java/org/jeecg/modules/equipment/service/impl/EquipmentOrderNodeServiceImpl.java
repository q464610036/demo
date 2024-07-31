package org.jeecg.modules.equipment.service.impl;

import org.jeecg.modules.common.entity.LoginUser;
import org.jeecg.modules.equipment.entity.EquipmentOrderNode;
import org.jeecg.modules.equipment.mapper.EquipmentOrderNodeMapper;
import org.jeecg.modules.equipment.service.IEquipmentOrderNodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 保养工单节点履历表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@Service
public class EquipmentOrderNodeServiceImpl extends ServiceImpl<EquipmentOrderNodeMapper, EquipmentOrderNode> implements IEquipmentOrderNodeService {

    @Override
    public boolean updateById(EquipmentOrderNode entity){
        this.buildUpdateEntity(entity);
        return super.updateById(entity);
    }

    private void buildUpdateEntity(EquipmentOrderNode entity){
        LoginUser login = null;
        entity.setUpdateBy(login.getId());
        entity.setUpdateTime(new Date());
    }
}
