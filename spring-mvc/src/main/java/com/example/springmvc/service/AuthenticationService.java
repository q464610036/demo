package com.example.springmvc.service;

import com.example.springmvc.dto.UserDTO;

public interface AuthenticationService {
    /**
     * 登录认证
     * @param dto
     * @return
     */
    boolean authentication(UserDTO dto);
}
