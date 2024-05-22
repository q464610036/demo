package com.example.springcloudnacosuser.module.user.service.serviceImpl;

import com.example.springcloudnacosuser.module.user.entity.User;
import com.example.springcloudnacosuser.module.user.mapper.UserMapper;
import com.example.springcloudnacosuser.module.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
