package com.lqm.springboot.dao;

import com.lqm.springboot.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LuQiMing
 * @title: GoodMapper
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2516:30
 */
@Mapper
public interface GoodsMapper {

    Goods selectByPrimaryKey(Integer id);

    Integer updateGoodCAS(Goods good);

}
