package com.lily.blog.dao;

import com.lily.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 功能描述：用户dao
 *
 * @ClassName: UserRepository
 * @Author: Lily.
 * @Date: 2019/8/25 22:06
 * @Version: V1.0
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
