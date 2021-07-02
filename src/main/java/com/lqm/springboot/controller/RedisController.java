package com.lqm.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.lqm.springboot.pojo.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LuQiMing
 * @title: RedisController
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/3016:21
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user) {
        stringRedisTemplate.opsForValue().set("user", JSON.toJSONString(user));
        return "success";
    }

    @PostMapping("/getUser")
    public User getUser() {
        return JSON.parseObject(stringRedisTemplate.opsForValue().get("user"), User.class);
    }

    @PostMapping("/addUsers")
    public Object addUsers(@RequestBody List<User> users) {
        stringRedisTemplate.opsForList().rightPushAll("users", users.stream().map(JSON::toJSONString).collect(Collectors.toList()));
        return "success";
    }

    @PostMapping("/getUsers")
    public Object getUsers() {
        List<User> users = new ArrayList<>();
        while (true) {
            User user = JSON.parseObject(stringRedisTemplate.opsForList().leftPop("users"), User.class);
            if (Objects.isNull(user)) {
                break;
            }
            users.add(user);
        }
        return users;
    }
}
