package com.lily.blog.dao;

import com.lily.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: TypeRepository
 * @Author: Lily.
 * @Date: 2019/8/26 15:36
 * @Version: V1.0
 */
public interface TypeRepository extends JpaRepository<Type,Long> {

    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);

}
