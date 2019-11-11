package com.oujiong.service.produce.service;

import com.oujiong.service.produce.model.Produce;


/**
  * @ClassName: ProduceService
  * @Description:  获取商品信息相关接口
  * @author xub
  * @date 2019/7/12 下午12:37
  */
public interface ProduceService {

    /**
     *  根据商品ID查找商品
     */
    Produce findById(int produceId);

    /**
     * 更新库存
     * @param produceId 商品ID
     * @param store     销售库存数量
     */
    /**
     * 更新库存
     * @param key 唯一值 分布式事务用
     * @param produceId 商品ID
     * @param store     销售库存数量
     */
    void updateStore(int produceId,int store,String key) throws Exception;
}
