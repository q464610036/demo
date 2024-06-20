package com.example.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String userName;
    private String password;
    private List<String> authorities;

    public UserDTO(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
