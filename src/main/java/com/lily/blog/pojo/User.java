package com.lily.blog.pojo;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: User
 * @Author: Lily.
 * @Date: 2019/8/25 20:43
 * @Version: V1.0
 */
@Data
@Entity
@Table(name = "t_user")
public class User {
    /**
     * id,主键
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 类型
    */
    private Integer type;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
