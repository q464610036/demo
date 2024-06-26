package com.example.springcloudsecurityoauth2uaa.user.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcloudsecurityoauth2uaa.uaa.config.DBUserDetailsManager;
import com.example.springcloudsecurityoauth2uaa.user.dto.AccountDTO;
import com.example.springcloudsecurityoauth2uaa.user.entity.Account;
import com.example.springcloudsecurityoauth2uaa.user.mapper.AccountMapper;
import com.example.springcloudsecurityoauth2uaa.user.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-05-23
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {
    @Autowired
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public boolean add(AccountDTO dto) {
        //主要是为了加密
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username(dto.getUsername())
                .password(dto.getPassword()).build();
        dbUserDetailsManager.createUser(user);
        return true;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        this.removeByIds(ids);
        return true;
    }
}
