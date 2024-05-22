package com.example.shardingjdbc.module.user.service;

import com.example.shardingjdbc.module.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
public interface IUserService extends IService<User> {

    List<User> addAndGetList(User user);
}
