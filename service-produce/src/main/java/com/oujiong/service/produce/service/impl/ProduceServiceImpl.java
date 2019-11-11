package com.oujiong.service.produce.service.impl;


import com.oujiong.service.produce.model.Produce;
import com.oujiong.service.produce.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 商品模块实现类
 *
 * @author xub
 * @date 2019/7/16 下午10:05
 */
@Slf4j
@Service
public class ProduceServiceImpl implements ProduceService {

    private static final Map<Integer, Produce> daoMap = new HashMap<>();

    /**
     * 模拟数据库商品数据
     */
    static {
        Produce p1 = new Produce(1, "苹果X", 9999, 10);
        Produce p2 = new Produce(2, "冰箱", 5342, 19);
        Produce p3 = new Produce(3, "洗衣机", 523, 90);

        daoMap.put(p1.getProduceId(), p1);
        daoMap.put(p2.getProduceId(), p2);
        daoMap.put(p3.getProduceId(), p3);
    }


    @Override
    public Produce findById(int id) {
        return daoMap.get(id);
    }

    @Override
    public void updateStore(int produceId, int store, String key) throws Exception {
        Produce produce = daoMap.get(produceId);

        // 如果实际库存小于库存 那么需要把这条数据记录到一张专门用于记录分布式事务的表，通过这个key当业务逻辑保证事务最终一致性
        if (produce.getStore() - store < 0) {
            /**
             * TODO 首先实际开发 不可能到这里才判断库存是否不足，而是下订单那边就确定好库存是否充足
             * 因为RocketMQ是最终一致性事务，不可能这边异常那边确已经告知用户下单正常，最后为了保证事务一致性在去修改这个订单为失败，用户会懵逼的
             */
            //模拟保存MQ异常表 用于人工处理 保证事务一致性
            log.info("库存不足，扣减失败。商品ID = {},商品当前库存 = {},所需库存 = {}，分布式事务key = {}", produceId, produce.getStore(), store, key);

            throw new Exception("库存不足，更新库存失败");
        }
        log.info("更新库存成功。商品ID = {},商品当前库存 = {},销售库存 = {}，分布式事务key = {}", produceId, produce.getStore(), store, key);

        log.info("===商品模块=== 本地事务执行成功,商品库存扣除成功");
    }
}
