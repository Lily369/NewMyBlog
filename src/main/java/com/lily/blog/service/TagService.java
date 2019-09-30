package com.lily.blog.service;

import com.lily.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: TagService
 * @Author: Lily.
 * @Date: 2019/8/26 22:40
 * @Version: V1.0
 */
public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Tag updateTag(Long id,Tag tag);

    void deleteTag(Long id);

    Tag getTagByName(String name);
}
