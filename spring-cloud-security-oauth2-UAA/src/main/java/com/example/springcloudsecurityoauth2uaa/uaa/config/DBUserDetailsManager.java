package com.example.springcloudsecurityoauth2uaa.uaa.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcloudsecurityoauth2uaa.user.entity.Account;
import com.example.springcloudsecurityoauth2uaa.user.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {
        Account account = new Account();
        account.setUsername(user.getUsername());
        account.setPassword(user.getPassword());
        accountMapper.insert(account);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Account> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Account::getUsername, username);
        Account account = accountMapper.selectOne(wrapper);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        } else {
            //用户-权限-资源方式，只能配置权限
//            return this.getUserByAuthorities(account);
            //用户-角色-权限-资源方式，既可以配置权限也可以配置角色，但好像只能二选一
            return this.getUserByRoles(account);
        }
    }

    /**
     * 用户-角色-权限-资源方式
     * @param account
     * @return
     */
    private UserDetails getUserByRoles(Account account ) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String role = null;
        if (account.getUsername().equals("admin")) {
            //角色
            role = "ADMIN";
            //权限
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "USER_ADD";
                }
            });
            authorities.add(()-> "USER_DELETE");
        }
        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .disabled(!(account.getEnable() == 0 ? true : false))
                .credentialsExpired(false) //证书已过期
                .accountLocked(false)
                .roles(role)
//                .authorities(authorities) //配置权限后角色就失效了
                .build();
    }
    /**
     * 用户-权限-资源方式
     * @param account
     * @return
     */
    private UserDetails getUserByAuthorities(Account account ){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        //数据库查询用户权限信息，这里就硬编码了。。
        if (account.getUsername().equals("admin")) {
            authorities.add(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "USER_ADD";
                }
            });
            authorities.add(()-> "USER_DELETE");

        }
            return new User(
                    account.getUsername(),
                    account.getPassword(),
                    account.getEnable() == 0 ? true : false,
                    true, //账号未过期过期
                    true, //凭证未过期
                    true, //用户未锁定
                    authorities //角色列表
                    );
    }
}
