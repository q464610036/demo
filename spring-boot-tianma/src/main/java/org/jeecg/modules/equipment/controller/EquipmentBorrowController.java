package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.jeecg.modules.equipment.vo.EquipmentBorrowDetailVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 借机表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-08-05
 */
@RestController
@Api(tags = "设备借机-借机申请")
@RequestMapping("/equipment/equipmentBorrow")
public class EquipmentBorrowController {

    @ApiOperation(value = "查询详情", notes = "非待审批单详情入口")
    @GetMapping("/detail/{id}")
    public Result<EquipmentBorrowDetailVo> detail(@PathVariable String id){
        return null;
    }
}
