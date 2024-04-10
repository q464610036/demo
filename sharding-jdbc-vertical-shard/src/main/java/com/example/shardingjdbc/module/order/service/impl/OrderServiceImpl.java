package com.example.shardingjdbc.module.order.service.impl;

import com.example.shardingjdbc.module.order.entity.Order;
import com.example.shardingjdbc.module.order.mapper.OrderMapper;
import com.example.shardingjdbc.module.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public void addBatchOrder(){
        for (int i=0; i<10;i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setOrderNo("O"+i);
            orderMapper.insert(order);
        }

    }
}
