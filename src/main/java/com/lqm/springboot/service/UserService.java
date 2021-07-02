package com.lqm.springboot.service;

import com.lqm.springboot.pojo.User;

import java.util.List;

/**
 * @author LuQiMing
 * @title: UserService
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2417:20
 */
public interface UserService {

    /**
     * 查询所有用户
     * @return
     */
    List<User> getUserList();

    /**
     * 查询一个用户
     * @param id
     * @return
     */
    User getOneUser(Integer id);

    /**
     * 插入用户
     * @param user
     */
    void insert(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param id
     */
    void delete(Integer id);

    void insertMove();
}
