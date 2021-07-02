package com.lqm.springboot.controller;

import com.lqm.springboot.service.GoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LuQiMing
 * @title: GoodsController
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2516:38
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @PostMapping("/updateGoodCAS")
    public void updateGoodCAS() {
        final Integer id = 1;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //用户1的请求
                goodsService.updateGoodCAS(id, 1);
            }
        });
        thread.start();
        //用户2的请求
        goodsService.updateGoodCAS(id, 2);

        System.out.println(goodsService.selectByPrimaryKey(id));
    }
}
