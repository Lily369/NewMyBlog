package com.lily.blog.web;

import com.lily.blog.pojo.Blog;
import com.lily.blog.service.BlogService;
import com.lily.blog.service.CommentService;
import com.lily.blog.service.TagService;
import com.lily.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述：
 *
 * @ClassName: IndexController
 * @Author: Lily.
 * @Date: 2019/8/25 14:09
 * @Version: V1.0
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    /**
     * 展示首页
     * @param pageable
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        return "index";
    }

    /**
     * 博客搜索
     * @param pageable
     * @param query
     * @param model
     * @return
     */
    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"createTime"},direction = Sort.Direction.DESC)Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

    @GetMapping("/sxblog/{id}/{sign}")
    public String ShangXia(@PathVariable Long id,@PathVariable Long sign){
        Blog blog = blogService.getBlog(id);
        Page<Blog> b = blogService.getUporDownBlogById(blog.getCreateTime(),sign);
        if (b.getContent().size()==0) {
            return "redirect:/";
        }
        Long id2 = b.getContent().get(0).getId();
        return "redirect:/blog/"+id2;
    }
}
