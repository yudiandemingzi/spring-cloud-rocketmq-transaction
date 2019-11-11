package com.oujiong.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 支持熔断降级 和 Feign
 *
 * @author xub
 * @date 2019/7/12 下午12:54
 */
@SpringBootApplication
@EnableFeignClients
//添加熔断降级注解
@EnableCircuitBreaker
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

}
