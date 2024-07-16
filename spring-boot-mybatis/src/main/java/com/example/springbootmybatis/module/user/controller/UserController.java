package com.example.springbootmybatis.module.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybatis.common.util.ResultUtil;
import com.example.springbootmybatis.module.user.entity.User;
import com.example.springbootmybatis.module.user.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/getList")
    public Object getList() {
        return ResultUtil.isSuccess(userMapper.selectList(null));
    }

    @GetMapping("/getPage")
    public Object getPage() {
        Page<User> page = new Page<>(1, 10);
//        List list = userMapper.selectPage(page, null).getRecords();
        List list = userMapper.getPage(page);
        return ResultUtil.isSuccess(list);
    }


    @GetMapping("/add")
    public Object add() {
        User user = new User();
        user.setName("李四");
        user.setSex(1);
        return ResultUtil.isSuccess(userMapper.insert(user));
    }

    @GetMapping("/del")
    public Object del(@RequestParam Integer id) {
        return ResultUtil.isSuccess(userMapper.deleteById(id));
    }
}
