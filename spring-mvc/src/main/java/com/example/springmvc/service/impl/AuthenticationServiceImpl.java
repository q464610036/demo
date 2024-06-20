package com.example.springmvc.service.impl;

import com.example.springmvc.dto.UserDTO;
import com.example.springmvc.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public boolean authentication(UserDTO dto) {
        boolean flag = false;
        List<UserDTO> userList = this.getUserList();
        for (UserDTO userDTO : userList) {
            if (dto.getUserName().equals(userDTO.getUserName()) && dto.getPassword().equals(userDTO.getPassword())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private List<UserDTO> getUserList(){
        List<UserDTO> list = Arrays.asList(
                new UserDTO("zhangsan","123456"),
                new UserDTO("lisi", "123456")
        );
        return list;
    }
}
