package com.lqm.springboot.service.impl;

import com.lqm.springboot.dao.GoodsMapper;
import com.lqm.springboot.pojo.Goods;
import com.lqm.springboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author LuQiMing
 * @title: GoodsServiceImpl
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2516:44
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateGoodCAS(Integer id, Integer decreaseNum) {
        Goods good = goodsMapper.selectByPrimaryKey(id);
        System.out.println(good);
        try {
            Thread.sleep(3000);     //模拟并发情况,不同的用户读取到同一个数据版本
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        good.setRemainingNumber(good.getRemainingNumber() - decreaseNum);
        int result = goodsMapper.updateGoodCAS(good);
        System.out.println(result == 1 ? "success" : "fail");
        return result == 1;
    }
}
