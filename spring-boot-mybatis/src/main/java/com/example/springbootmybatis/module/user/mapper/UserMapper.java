package com.example.springbootmybatis.module.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybatis.module.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> getPage(Page<User> page);
}
