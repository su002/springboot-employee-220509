package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.noon.RequirePremission;
import cn.wolfcode.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DepartmentMapperTest {
    @Autowired
    DepartmentService departmentMap;
    @Autowired
    ApplicationContext context;

    /**
     * 在controller 类上 加上@controller
     * 将该类注入到容器中
     * 找到该类的 加有权限的的注解  @
     * 从控制器中获取
     * 的方法名
     * 从有注解的方法上面获取自定义注解,
     * 获取注解里面的 name 与权限的
     */
    @Test
    public void test() {
        List<String> objects = new ArrayList<>();
        List<String> list = departmentMap.selectRoleAll();
        Employee employee = new Employee();
        Permission permission = new Permission();
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(Controller.class);
        Collection<Object> values = beansWithAnnotation.values();
        for (Object value : values) {
            Method[] declaredMethod = value.getClass().getDeclaredMethods();
            for (Method method : declaredMethod) {
                RequirePremission annotation = method.getAnnotation(RequirePremission.class);
                if (annotation != null) {
                    String name = annotation.name();
                    String expression = annotation.expression();

                    permission.setName(name);
                    permission.setExpression(expression);
                    if (!list.contains(expression)) {
                        departmentMap.insertRole(expression);

                    } else {
                        departmentMap.updateRole(name, expression);

                    }
                }
            }
        }
    }



}