package com.lily.blog.service;

import com.lily.blog.pojo.Comment;

import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: CommentService
 * @Author: Lily.
 * @Date: 2019/8/28 21:16
 * @Version: V1.0
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

}
