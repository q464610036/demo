package com.example.shardingjdbc.module.user.controller;

import com.example.shardingjdbc.common.util.ResultUtil;
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

    /**
     * 查询，读写分离论证
     * @return
     */
    @GetMapping("/getList")
    public Object getList() {
        return ResultUtil.isSuccess(userMapper.selectList(null));
    }

    /**
     * 新增，读写分离论证
     * @return
     */
    @GetMapping("/add")
    public Object add() {
        User user = new User();
        user.setName("李四");
        user.setSex(1);
        return ResultUtil.isSuccess(userMapper.insert(user));
    }

    /**
     * 删除，读写分离论证
     * @param id
     * @return
     */
    @GetMapping("/del")
    public Object del(@RequestParam Integer id) {
        return ResultUtil.isSuccess(userMapper.deleteById(id));
    }

    /**
     * 插入后查询列表，读写分离时，同一事务内的读写都在master完成
     * @return
     */
    @GetMapping("/addAndGetList")
    public Object addAndGetList() {
        User user = new User();
        user.setName("李四");
        user.setSex(1);
        return ResultUtil.isSuccess(userService.addAndGetList(user));
    }
}
