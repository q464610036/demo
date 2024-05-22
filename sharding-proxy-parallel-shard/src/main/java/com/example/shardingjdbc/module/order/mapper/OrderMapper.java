package com.example.shardingjdbc.module.order.mapper;

import com.example.shardingjdbc.module.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shardingjdbc.module.order.vo.OrderVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Select({"select t1.order_no, sum(t2.price * t2.count) as amount "+
            " from t_order t1 " +
            "left join t_order_item t2 on t1.order_no = t2.order_no " +
            "group by t1.order_no"
    })
    List<OrderVO> getOrderAmount();
}
