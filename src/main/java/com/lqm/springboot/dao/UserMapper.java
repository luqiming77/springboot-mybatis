package com.lqm.springboot.dao;

import com.lqm.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author LuQiMing
 * @title: UserMapper
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2417:17
 */
@Mapper
public interface UserMapper{
    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 查一个
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 新增用户
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 查询所有用户
     * @return
     */
    List<User> getAllList();
}