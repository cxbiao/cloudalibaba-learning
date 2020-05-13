package com.bryan.cloudalibaba.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@TableName("order_tb")
@Builder
@Data
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String goods;
    private Integer count;
    private BigDecimal money;
}
