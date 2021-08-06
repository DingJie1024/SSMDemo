package edu.dj.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=====拦截住=====");
        //处于登录页面
        if (request.getRequestURI().contains("login")){
            return true;
        }
        //用户登录
        if (request.getSession().getAttribute("logined")!=null){
            return true;
        }

        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
