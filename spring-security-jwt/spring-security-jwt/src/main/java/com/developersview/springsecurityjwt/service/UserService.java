package com.developersview.springsecurityjwt.service;

import com.developersview.springsecurityjwt.entity.UserInfo;
import com.developersview.springsecurityjwt.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added to system";
    }
}
