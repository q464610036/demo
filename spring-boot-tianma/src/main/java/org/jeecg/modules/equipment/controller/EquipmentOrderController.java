package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.EquipmentOrderQueryPageDto;
import org.jeecg.modules.equipment.vo.EquipmentOrderDetailVo;
import org.jeecg.modules.equipment.vo.EquipmentOrderListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 保养工单表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@RestController
@Api(tags = "设备保养-工单管理")
@RequestMapping("/equipment/equipmentOrder")
public class EquipmentOrderController {

    @ApiOperation("查询分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentOrderListVo>> getPage(@RequestBody @Validated EquipmentOrderQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询模版详情")
    @GetMapping("/detail/{id}")
    public Result<EquipmentOrderDetailVo> detail(@PathVariable String id){
        return null;
    }
}
