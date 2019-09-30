package com.lily.blog.web;

import com.lily.blog.pojo.Type;
import com.lily.blog.service.BlogService;
import com.lily.blog.service.TypeService;
import com.lily.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 功能描述：前端分页分类展示
 *
 * @ClassName: TypeShowController
 * @Author: Lily.
 * @Date: 2019/8/29 8:33
 * @Version: V1.0
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }

}
