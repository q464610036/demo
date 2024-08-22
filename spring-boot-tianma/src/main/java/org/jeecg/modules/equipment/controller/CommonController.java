package org.jeecg.modules.equipment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.common.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 借机表 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2024-08-05
 */
@RestController
@Api(tags = "公共类")
@RequestMapping("/sys/common")
public class CommonController {

    @ApiOperation(value = "查询SubUnit type")
    @GetMapping("/getSubUnitType/{unitType}")
    public Result<List<String>> getSubUnitType(@PathVariable String unitType){
        return null;
    }
}
