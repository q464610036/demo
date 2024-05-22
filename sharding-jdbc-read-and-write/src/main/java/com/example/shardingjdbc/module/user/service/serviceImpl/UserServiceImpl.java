package com.example.shardingjdbc.module.user.service.serviceImpl;

import com.example.shardingjdbc.module.user.entity.User;
import com.example.shardingjdbc.module.user.mapper.UserMapper;
import com.example.shardingjdbc.module.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Transactional
    @Override
    public List<User> addAndGetList(User user) {
        userMapper.insert(user);
        return userMapper.selectList(null);
    }
}
