package com.example.shardingjdbc.module.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shardingjdbc.module.dict.entity.Dict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
public interface IDictService extends IService<Dict> {
    void add();
}
