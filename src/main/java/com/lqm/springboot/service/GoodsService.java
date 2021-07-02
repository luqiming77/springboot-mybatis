package com.lqm.springboot.service;

import com.lqm.springboot.pojo.Goods;

/**
 * @author LuQiMing
 * @title: GoodsService
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2516:40
 */
public interface GoodsService {

    Goods selectByPrimaryKey(Integer id);

    Boolean updateGoodCAS(Integer id, Integer decreaseNum);
}
