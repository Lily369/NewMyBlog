package com.lily.blog.dao;

import com.lily.blog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: CommentRepository
 * @Author: Lily.
 * @Date: 2019/8/28 21:19
 * @Version: V1.0
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

}
