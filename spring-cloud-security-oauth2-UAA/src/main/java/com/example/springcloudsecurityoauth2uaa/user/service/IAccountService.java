package com.example.springcloudsecurityoauth2uaa.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springcloudsecurityoauth2uaa.user.dto.AccountDTO;
import com.example.springcloudsecurityoauth2uaa.user.entity.Account;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-05-23
 */
public interface IAccountService extends IService<Account> {

    boolean add(AccountDTO dto);

    boolean delete(List<Integer> ids);

    default void test(){};

    default void test(int a){};
}
