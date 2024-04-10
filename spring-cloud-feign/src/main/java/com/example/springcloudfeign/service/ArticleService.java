package com.example.springcloudfeign.service;

import java.util.List;

public interface ArticleService {
    String getOne(Long id);

    List<String> getOneBatch(List<Long> ids);
}
