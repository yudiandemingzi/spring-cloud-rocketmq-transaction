package com.oujiong.service.order.service;


/**
 * @Description: 订单业务类
 *
 * @author xub
 * @date 2019/7/12 下午12:57
 */
public interface ProduceOrderService {

     /**
      * @Description: 下单接口
      * @author xub
      */
     int save(int userId, int produceId, int total);
}
