package com.lily.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：登录拦截
 *  该类继承一个Springboot控制器,重写preHandle方法，返回true则继续执行，false反之
 * @ClassName: LoginInterceptor
 * @Author: Lily.
 * @Date: 2019/8/26 14:16
 * @Version: V1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
