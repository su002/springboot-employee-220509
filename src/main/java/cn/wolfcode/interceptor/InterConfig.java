package cn.wolfcode.interceptor;

import cn.wolfcode.domain.Employee;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class  InterConfig  implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee user =(Employee) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/static/login.html");
            return false;
        }
        return true;
    }
}
