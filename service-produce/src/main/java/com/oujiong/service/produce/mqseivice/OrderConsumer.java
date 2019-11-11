package com.oujiong.service.produce.mqseivice;

import com.alibaba.fastjson.JSONObject;
import com.oujiong.service.produce.config.Jms;
import com.oujiong.service.produce.service.ProduceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author xub
 * @Description: 消费端跟之前普通消费没区别
 * 因为分布式事务主要是通过 生产端控制 消息的发送
 * @date 2019/7/15 下午12:43
 */
@Slf4j
@Component
public class OrderConsumer {

    private DefaultMQPushConsumer consumer;

    private String consumerGroup = "produce_consumer_group";

    public OrderConsumer(@Autowired Jms jms, @Autowired ProduceService produceService) throws MQClientException {
        //设置消费组
        consumer = new DefaultMQPushConsumer(consumerGroup);
        // 添加服务器地址
        consumer.setNamesrvAddr(jms.getNameServer());
        // 添加订阅号
        consumer.subscribe(jms.getOrderTopic(), "*");
        // 监听消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            MessageExt msg = msgs.get(0);
            String message = new String(msgs.get(0).getBody());
            JSONObject jsonObject = JSONObject.parseObject(message);
            Integer productId = jsonObject.getInteger("productId");
            Integer total = jsonObject.getInteger("total");
            String key = msg.getKeys();
            log.info("消费端消费消息，商品ID={},销售数量={}",productId,total);
            try {
                produceService.updateStore(productId, total, key);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                log.info("消费失败，进行重试，重试到一定次数 那么将该条记录记录到数据库中，进行如果处理");
                e.printStackTrace();
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });

        consumer.start();
        System.out.println("consumer start ...");
    }

}
