package com.lily.blog.service.impl;

import com.lily.blog.dao.UserRepository;
import com.lily.blog.pojo.User;
import com.lily.blog.service.UserService;
import com.lily.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @ClassName: UserServiceImpl
 * @Author: Lily.
 * @Date: 2019/8/25 22:06
 * @Version: V1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}