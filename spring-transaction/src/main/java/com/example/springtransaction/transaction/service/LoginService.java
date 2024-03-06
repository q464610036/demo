package com.example.springtransaction.transaction.service;

public interface LoginService {
    void login(Integer userId);

    void save(Integer userId);

    void update(Integer userId);
}
