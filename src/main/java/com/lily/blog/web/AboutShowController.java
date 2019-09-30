package com.lily.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述：关于我
 *
 * @ClassName: AboutShowController
 * @Author: Lily.
 * @Date: 2019/8/29 18:27
 * @Version: V1.0
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }

}
