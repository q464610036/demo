package com.example.springcloudnacosuser.module.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.springcloudnacosuser.common.util.ResultUtil;
import com.example.springcloudnacosuser.config.UserConfig;
import com.example.springcloudnacosuser.module.user.entity.User;
import com.example.springcloudnacosuser.module.user.mapper.UserMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
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

    @Resource
    private UserConfig userConfig;

    @SentinelResource("getList-sentinel")
    @GetMapping("/getList")
    public Object getList(@RequestParam(required = false) Integer state) {
        System.out.println("Enable:"+userConfig.getEnable());
        if (!CollectionUtils.isEmpty(userConfig.getBlackList())) {
            for (Integer black : userConfig.getBlackList()) {
                System.out.println("blackUserId:"+black);
            }
        }
        if (state != null) {
            System.out.println("state:"+state);
        }
        return ResultUtil.isSuccess(userMapper.selectList(null));
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

    @GetMapping("/getOne")
    public User getOne(Integer id) {
        return userMapper.selectById(id);
    }

    @GetMapping("/getOneBatch")
    public List<User> getOneBatch(List<Integer> ids) {
        return userMapper.selectBatchIds(ids);
    }
}
