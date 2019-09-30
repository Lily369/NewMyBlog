package com.lily.blog.service;

import com.lily.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能描述：分类
 *
 * @ClassName: TypeService
 * @Author: Lily.
 * @Date: 2019/8/26 15:33
 * @Version: V1.0
 */
public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

}
