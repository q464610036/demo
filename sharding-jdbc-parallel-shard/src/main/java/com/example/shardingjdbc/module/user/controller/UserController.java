package com.example.shardingjdbc.module.user.controller;

import com.example.shardingjdbc.common.util.ResultUtil;
import com.example.shardingjdbc.module.dict.service.IDictService;
import com.example.shardingjdbc.module.order.entity.Order;
import com.example.shardingjdbc.module.order.service.IOrderService;
import com.example.shardingjdbc.module.user.entity.User;
import com.example.shardingjdbc.module.user.mapper.UserMapper;
import com.example.shardingjdbc.module.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @Resource
    private IOrderService orderService;

    @Resource
    private IDictService dictService;

    /**
     * 批量写入订单，水平分库分表论证，订单表路由、订单明细表路由论证
     * @return
     */
    @GetMapping("/addBatchOrder")
    public Object addBatchOrder() {
        orderService.addBatchOrder();
        return ResultUtil.isSuccess(true);
    }

    /**
     * 统计每个订单的总金额，关联表功能理论正，防止两个表不可能有关联得查询出现
     * @return
     */
    @GetMapping("/getOrderAmount")
    public Object getOrderAmount() {
        return ResultUtil.isSuccess(orderService.getOrderAmount());
    }

    /**
     * 写入数据字典，绑定表功能论证
     * @return
     */
    @GetMapping("/addDict")
    public Object addDict() {
        dictService.add();
        return ResultUtil.isSuccess(true);
    }
}
