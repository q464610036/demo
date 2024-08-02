package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.EquipmentIncompleteQueryPageDto;
import org.jeecg.modules.equipment.vo.EquipmentIncompleteListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 设备残件表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-07-24
 */
@RestController
@Api(tags = "设备保养-残件管理")
@RequestMapping("/equipment/equipmentIncomplete")
public class EquipmentIncompleteController {

    @ApiOperation("查询分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentIncompleteListVo>> getPage(@RequestBody @Validated EquipmentIncompleteQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询列表")
    @PostMapping("/getList")
    public Result<List<EquipmentIncompleteListVo>> getList(@RequestBody @Validated EquipmentIncompleteQueryPageDto dto){
        return null;
    }
}
