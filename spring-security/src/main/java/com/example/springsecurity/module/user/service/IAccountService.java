package com.example.springsecurity.module.user.service;

import com.example.springsecurity.module.user.dto.AccountDTO;
import com.example.springsecurity.module.user.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

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
