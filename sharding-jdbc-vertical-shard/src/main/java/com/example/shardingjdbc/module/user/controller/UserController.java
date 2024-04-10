package com.example.shardingjdbc.module.user.controller;

import com.example.shardingjdbc.common.util.ResultUtil;
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

    @Resource
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    /**
     * 写入用户和订单，垂直分库论证
     * @return
     */
    @GetMapping("/addUserAndOrder")
    public Object addUserAndOrder() {
        userService.addUserAndOrder();
        return ResultUtil.isSuccess(true);
    }

}
