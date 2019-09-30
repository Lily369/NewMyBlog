package com.lily.blog.service.impl;

import com.lily.blog.NotFoundException;
import com.lily.blog.dao.TagRepository;
import com.lily.blog.pojo.Tag;
import com.lily.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @ClassName: TagServiceImpl
 * @Author: Lily.
 * @Date: 2019/8/26 22:46
 * @Version: V1.0
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertTolist(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertTolist(String ids){
        List<Long> list = new ArrayList<>();
        if (ids != null && !"".equals(ids)) {
            String[] idarray = ids.split(",");
            for (String s : idarray) {
                Tag tag1 = null;
                try {
                    tag1 = tagRepository.findById(new Long(s)).get();
                } catch (Exception e) {
                    tag1 = null;
                }
                if(checkStrIsNum01(s) && tag1!=null){
                        list.add(new Long(s));
                }else{
                    Tag tag = new Tag();
                    tag.setName(s);
                    tagRepository.save(tag);
                    list.add(tagRepository.findByName(s).getId());
                }
            }
        }
        return list;
    }

    /**
     * 利用Java的character.isDigit方法进行处理
     */
    public boolean checkStrIsNum01(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
