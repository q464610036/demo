package com.example.shardingjdbc.module.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenmengfei
 * @since 2024-04-10
 */
@TableName("t_order_item")
@Data
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    //IdType.AUTO的策略是把id清空，使用数据库的自增策略或者shardingSphere的分布式序列策略
//    @TableId(value = "id", type = IdType.AUTO)
    //mybatis的分布式id雪花算法，数据库主键id自增要取消掉
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 名称
     */
    private String orderNo;

    /**
     * 删除状态：0未删除，1已删除
     */
    private Integer deleteFlag;

    private BigDecimal price;

    private Integer count;

    @Override
    public String toString() {
        return "Order{" +
            "id = " + id +
            ", userId = " + userId +
            ", orderNo = " + orderNo +
            ", deleteFlag = " + deleteFlag +
        "}";
    }
}
