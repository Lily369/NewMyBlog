package com.lily.blog.service;

import com.lily.blog.pojo.User;

/**
 * 功能描述：
 *
 * @ClassName: UserService
 * @Author: Lily.
 * @Date: 2019/8/25 22:05
 * @Version: V1.0
 */
public interface UserService {
    User checkUser(String username, String password);
}
