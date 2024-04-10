package com.example.shardingjdbc.module.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingjdbc.module.order.entity.Order;
import com.example.shardingjdbc.module.order.entity.OrderItem;
import com.example.shardingjdbc.module.order.mapper.OrderItemMapper;
import com.example.shardingjdbc.module.order.mapper.OrderMapper;
import com.example.shardingjdbc.module.order.service.IOrderService;
import com.example.shardingjdbc.module.order.vo.OrderVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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

    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public void addBatchOrder(){
        for (int i=0; i<10;i++) {
            //每个user生成10个订单
            for (int j=0; j<10; j++) {
                Order order = new Order();
                order.setUserId(i);
                order.setOrderNo("O"+j);
                orderMapper.insert(order);
                //每个订单生成10个item
                for (int z=0; z<10; z++) {
                    OrderItem item = new OrderItem();
                    item.setOrderNo(order.getOrderNo());
                    item.setUserId(order.getUserId());
                    item.setPrice(new BigDecimal(z+""));
                    item.setCount(z);
                    orderItemMapper.insert(item);
                }
            }
        }
    }

    @Override
    public List<OrderVO> getOrderAmount(){
        return orderMapper.getOrderAmount();
    }
}
