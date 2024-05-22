package com.example.springcloudfeign.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Override
    public String getOne(Long id) {
        return "请求成功";
    }

    @Override
    public List<String> getOneBatch(List<Long> ids){
        List<String> list = new ArrayList<>();
        for (Long id : ids) {
            list.add(id+"请求成功");
        }
        return list;
    }
}
