package com.lily.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能描述：拦截器配置类
 *
 * @ClassName: WebConfig
 * @Author: Lily.
 * @Date: 2019/8/26 14:22
 * @Version: V1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截的路径
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**").excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
