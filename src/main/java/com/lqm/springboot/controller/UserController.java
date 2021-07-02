package com.lqm.springboot.controller;

import com.lqm.springboot.pojo.User;
import com.lqm.springboot.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuQiMing
 * @title: UserController
 * @projectName springboot-mybatis
 * @description TODO
 * @date 2021/6/2417:17
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/getUserList")
    public List<User> getUserList() {
        List<User> list = userService.getUserList();
        return list;
    }

    @PostMapping("/getOneUser")
    public User getOneUser(Integer id) {
        return userService.getOneUser(id);

    }

    @PostMapping("/insert")
    public void insert(User user) {
        userService.insert(user);
    }

    @PostMapping("/update")
    public void update(User user) {
        userService.update(user);
    }

    @PostMapping("/delete")
    public void delete(Integer id) {
        userService.delete(id);
    }

    @PostMapping("/insertMove")
    public void insertMove() {
        userService.insertMove();
    }

}
