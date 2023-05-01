package com.developersview.springsecurityjwt.controller;

import com.developersview.springsecurityjwt.entity.UserInfo;
import com.developersview.springsecurityjwt.service.UserDetailsServiceImpl;
import com.developersview.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-jwt/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }
}
