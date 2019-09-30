package com.lily.blog.dao;

import com.lily.blog.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: TagRepository
 * @Author: Lily.
 * @Date: 2019/8/26 22:38
 * @Version: V1.0
 */
public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);

}
