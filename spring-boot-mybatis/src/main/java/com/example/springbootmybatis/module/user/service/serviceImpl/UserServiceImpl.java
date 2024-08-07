package com.example.springbootmybatis.module.user.service.serviceImpl;

import com.example.springbootmybatis.module.user.entity.User;
import com.example.springbootmybatis.module.user.mapper.UserMapper;
import com.example.springbootmybatis.module.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public void saveBatch(List<User> list) {
        this.saveBatch(list);
    }
}
