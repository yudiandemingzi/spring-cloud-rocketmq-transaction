package com.oujiong.service.produce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
  * @ClassName: Produce
  * @Description: 商品实体信息
  * @author xub
  * @date 2019/7/12 下午12:33
  */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produce implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Integer produceId;

    /**
     * 商品名称
     */
    private String produceName;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 商品库存
     */
    private Integer store;

}
