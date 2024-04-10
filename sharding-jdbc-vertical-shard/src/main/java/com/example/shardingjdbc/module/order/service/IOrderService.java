package com.example.shardingjdbc.module.order.service;

import com.example.shardingjdbc.module.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
public interface IOrderService extends IService<Order> {
    void addBatchOrder();
}
