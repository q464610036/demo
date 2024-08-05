package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.EquipmentTempUpkeepAndBorrowSaveDto;
import org.jeecg.modules.equipment.dto.EquipmentTempUpkeepQueryPageDto;
import org.jeecg.modules.equipment.vo.EquipmentTempUpkeepListVo;
import org.jeecg.modules.equipment.vo.EquipmentTempUpkeepSaveResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 设备临时保养表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@RestController
@Api(tags = "设备保养-临时保养管理")
@RequestMapping("/equipment/equipmentTempUpkeep")
public class EquipmentTempUpkeepController {
    @ApiOperation("查询分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentTempUpkeepListVo>> getPage(@RequestBody @Validated EquipmentTempUpkeepQueryPageDto dto){
        return null;
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public Result<EquipmentTempUpkeepSaveResultVo> add(@RequestBody @Validated EquipmentTempUpkeepAndBorrowSaveDto dto){
        return null;
    }
}
