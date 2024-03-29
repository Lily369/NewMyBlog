package com.lily.blog.web.admin;

import com.lily.blog.pojo.Tag;
import com.lily.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * 功能描述：标签控制层
 *
 * @ClassName: TagController
 * @Author: Lily.
 * @Date: 2019/8/26 22:54
 * @Version: V1.0
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 标签列表
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort={"id"},direction = Sort.Direction.DESC)Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 跳转到添加标签
     * @param model
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 跳转到修改标签
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * 添加标签
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签哟");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message","很遗憾,添加失败了哦");
        }else {
            attributes.addFlashAttribute("message","恭喜你,添加成功了呢");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 修改标签
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类哟");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null) {
            attributes.addFlashAttribute("message","很遗憾,修改失败了哦");
        }else{
            attributes.addFlashAttribute("message","恭喜你,修改成功了呢");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功!");
        return "redirect:/admin/tags";
    }

}
