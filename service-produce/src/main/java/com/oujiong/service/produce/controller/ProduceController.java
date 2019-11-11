package com.oujiong.service.produce.controller;


import com.alibaba.fastjson.JSON;
import com.oujiong.service.produce.service.ProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author xub
 * @Description: 商品服务对外提供接口
 * @date 2019/7/12 下午12:43
 */
@RestController
@RequestMapping("/api/v1/produce")
public class ProduceController {

    @Autowired
    private ProduceService produceService;

    /**
     * 根据主键ID获取商品
     */
    @GetMapping("/find")
    public String findById(@RequestParam(value = "produceId") int produceId) {
        return JSON.toJSONString(produceService.findById(produceId));

    }

}
