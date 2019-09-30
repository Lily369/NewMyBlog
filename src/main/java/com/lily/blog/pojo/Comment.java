package com.lily.blog.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：评论实体类
 *
 * @ClassName: Comment
 * @Author: Lily.
 * @Date: 2019/8/25 20:38
 * @Version: V1.0
 */
@Data
@Entity
@Table(name = "t_comment")
public class Comment {
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
    * 邮箱
    */
    private String email;
    /**
    * 内容
    */
    private String content;
    /**
    * 头像
    */
    private String avatar;
    /**
    * 创建时间
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @ManyToOne
    private Blog blog;
    /**
     * 子集评论
     */
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    /**
     * 父级评论
     */
    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;

}