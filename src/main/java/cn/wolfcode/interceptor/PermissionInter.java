package cn.wolfcode.interceptor;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.noon.RequirePremission;
import cn.wolfcode.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class PermissionInter implements HandlerInterceptor {
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee user =(Employee) request.getSession().getAttribute("user");
//        如果是超级管理员直接放行
        if (user != null && user.isAdmin()) {
            return true;
        }
//        转换
        HandlerMethod method =(HandlerMethod) handler;
        RequirePremission anno = method.getMethodAnnotation(RequirePremission.class);

//        如果没有加 @RequirePremission 注解 则放行
        if (anno == null) {
            return true;
        }
        if (anno != null){
            String name = anno.name();
            String expression = anno.expression();
            List<String> list = permissionService.selectByEmployeeId(user.getId());
            if (list.contains(expression)){
                return true;
            }
        }
//        没有权限 , 返回指定页面
        response.sendRedirect("/common/nopermission");
        return false;
    }
}
