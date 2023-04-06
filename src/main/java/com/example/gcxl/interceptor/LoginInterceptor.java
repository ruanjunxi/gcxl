package com.example.gcxl.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eason
 * @ClassName:LoginInterceptor
 * @data:2022/4/16 17:11
 * 将登录页面设置为白名单
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session中是否有用户数据，若有则放行，若无则重定向到登录页面
     *
     * @param request
     * @param response
     * @param handler
     * @return True则放行，FALSE则拦截
     * @throws Exception
     */
    @Override
//    在调用所有处理请求的方法之前被自动调用执行的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("username");
        if (obj==null){
            response.sendRedirect("http://172.17.169.217:8082/loginPage");//重定向的目录html页面
            return false;
        }
        return true;
    }

    @Override
//    在modolandview对象返回之后被调用的方法
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
//    在整个请求所有福暗恋的资源被执行完毕最后所执行的方法
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
