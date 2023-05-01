package com.developersview.springsecurityjwt.service;

import com.developersview.springsecurityjwt.entity.UserInfo;
import com.developersview.springsecurityjwt.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
