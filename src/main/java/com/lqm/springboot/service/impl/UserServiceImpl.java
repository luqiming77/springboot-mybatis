package com.lqm.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.lqm.springboot.dao.UserMapper;
import com.lqm.springboot.pojo.User;
import com.lqm.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author LuQiMing
 * @title: UserServiceImpl
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2417:21
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /***
     * 注入redis
     */
    @Resource
    private StringRedisTemplate template;

    private final String packages = UserServiceImpl.class.getPackage().toString();


    @Override
    public List<User> getUserList() {
        List<User> list = null;
        System.out.println(packages + ".findAll");
        //查询key是否存在，存在true反之false
        boolean flag = template.hasKey(packages + ".findAll");
        if (!flag) {
            list = userMapper.getAllList();
            System.out.println(list);
            //存入redis中,参数(key,value);
            template.opsForValue().set(packages + ".findAll", JSON.toJSONString(list));
        } else {
            list = JSON.parseArray(template.opsForValue().get(packages + ".findAll"), User.class);
            //根据key取出对应的value
            System.out.println(template.opsForValue().get(packages + ".findAll"));
        }
        return list;
    }

    @Override
    public User getOneUser(Integer id) {
        User user = null;
        boolean flag = template.hasKey(packages+".findById");
        if(!flag){
            if(id != null){
                user = userMapper.selectByPrimaryKey(id);
                String json = JSON.toJSONString(user);
                template.opsForValue().set(packages+".findById", json);
            }
        }else{
            String json = template.opsForValue().get(packages+".findById");
            user = JSON.parseObject(json,User.class);
        }
        return user;

    }


    @Override
    public void insert(User user) {
        List<User> list = JSON.parseArray(template.opsForValue().get(packages+".findAll"),User.class);
        boolean flag = list.contains(user);
        if(!flag){
            //不存在
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
            Integer id = user.getId();
            User newUser = userMapper.selectByPrimaryKey(user.getId());
            list.add(newUser);
            template.opsForValue().set(packages+".findAll",JSON.toJSONString(list));
        }
    }


    @Override
    public void update(User user) {
        List<User> list = JSON.parseArray(template.opsForValue().get(packages+".findAll"),User.class);
        User oldUser = userMapper.selectByPrimaryKey(user.getId());
        list.remove(oldUser);
        userMapper.updateByPrimaryKeySelective(user);
        User newUser = userMapper.selectByPrimaryKey(user.getId());
        list.add(newUser);
        template.opsForValue().set(packages+".findAll",JSON.toJSONString(list));
    }

    @Override
    public void delete(Integer id) {
        List<User> list = JSON.parseArray(template.opsForValue().get(packages+".findAll"),User.class);
        User user = userMapper.selectByPrimaryKey(id);
        list.remove(user);
        userMapper.deleteByPrimaryKey(id);
        template.opsForValue().set(packages+".findAll",JSON.toJSONString(list));
    }

    @Override
    public void insertMove() {
        for (int i = 1; i <= 100000; i++) {
            User user = new User();
            user.setAge(i);
            user.setCreateTime(new Date());
            user.setName(UUID.randomUUID().toString().substring(1).toLowerCase());
            user.setWealth((long) ((Math.random() * 10) * Math.pow(10, 7)));

            userMapper.insertSelective(user);
        }
    }


}