package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.common.page.PageInfo;
import org.jeecg.modules.equipment.dto.DeleteDto;
import org.jeecg.modules.equipment.dto.EquipmentIncompletePSaveListDto;
import org.jeecg.modules.equipment.dto.EquipmentIncompleteQueryPageDto;
import org.jeecg.modules.equipment.vo.EquipmentIncompletePListVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 点位残件表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-08-19
 */
@RestController
@Api(tags = "设备保养-备品备件残件管理")
@RequestMapping("/equipment/equipmentIncompleteP")
public class EquipmentIncompletePController {
    @ApiOperation("查询分页")
    @PostMapping("/getPage")
    public Result<PageInfo<EquipmentIncompletePListVo>> getPage(@RequestBody @Validated EquipmentIncompleteQueryPageDto dto){
        return null;
    }

    @ApiOperation("查询列表")
    @PostMapping("/getList")
    public Result<List<EquipmentIncompletePListVo>> getList(@RequestBody @Validated EquipmentIncompleteQueryPageDto dto){
        return null;
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public Result<String> add(@RequestBody @Validated EquipmentIncompletePSaveListDto dto){
        return null;
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody @Validated DeleteDto dto){
        return null;
    }
}
