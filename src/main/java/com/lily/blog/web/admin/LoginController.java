package com.lily.blog.web.admin;

import com.lily.blog.pojo.User;
import com.lily.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 功能描述：后台登录控制层
 *
 * @ClassName: LoginController
 * @Author: Lily.
 * @Date: 2019/8/25 22:11
 * @Version: V1.0
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * GetMapping表示get方式请求
     * @return 登录页面
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误!");
            return "redirect:/admin";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/admin";
    }

}
