package com.example.shardingjdbc.module.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingjdbc.module.dict.entity.Dict;
import com.example.shardingjdbc.module.dict.mapper.DictMapper;
import com.example.shardingjdbc.module.dict.service.IDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public void add(){
        Dict dict = new Dict();
        dict.setDictType("Language");
        dictMapper.insert(dict);
    }

}
