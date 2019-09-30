package com.lily.blog.web.admin;

import com.lily.blog.pojo.Blog;
import com.lily.blog.pojo.User;
import com.lily.blog.service.BlogService;
import com.lily.blog.service.TagService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 功能描述：博客控制层
 *
 * @ClassName: BlogController
 * @Author: Lily.
 * @Date: 2019/8/26 11:31
 * @Version: V1.0
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 跳转到博客列表
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

    /**
     * 局部刷新，上一页/下一页
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size=2,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    /**
     * 跳转到博客新增
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return INPUT;
    }

    /**
     * 添加/修改 博客
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes,HttpSession session){
        //设置添加用户
        blog.setUser((User) session.getAttribute("user"));
        //设置分类
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置标签
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == null) {
             b = blogService.saveBlog(blog);
        }else {
            b = blogService.updateBlog(blog.getId(),blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message","很遗憾,操作失败了哦");
        }else{
            attributes.addFlashAttribute("message","恭喜你,操作成功了呢");
        }
        return REDIRECT_LIST;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    /**
     * 跳转到博客修改
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return INPUT;
    }

    /**
     * 删除博客
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }


}
