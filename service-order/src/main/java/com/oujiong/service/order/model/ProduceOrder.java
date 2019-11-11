package com.oujiong.service.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品订单实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProduceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  订单ID
     */
    private Integer orderId;

    /**
     * 商品名称
     */
    private String produceName;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 价格,分
     */
    private Integer price;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;
}
