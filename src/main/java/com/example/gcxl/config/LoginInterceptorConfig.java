package com.example.gcxl.config;

import com.example.gcxl.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eason
 * @ClassName:LoginInterceptorConfig
 * @data:2022/4/16 17:24
 */
//@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    HandlerInterceptor interceptor = new LoginInterceptor();

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置白名单：存放在list中
        List<String> patterns = new ArrayList<>();
//        patterns.add("http://172.17.169.217:8082/home");
//        patterns.add("http://172.17.169.217:8082/purchase_order");
        patterns.add("http://172.17.169.217:8082/loginPage");


//        拦截器的注册
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }

}
