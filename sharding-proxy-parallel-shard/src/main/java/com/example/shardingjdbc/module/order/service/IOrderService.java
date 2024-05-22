package com.example.shardingjdbc.module.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shardingjdbc.module.order.entity.Order;
import com.example.shardingjdbc.module.order.vo.OrderVO;

import java.util.List;

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
    List<OrderVO> getOrderAmount();
}
