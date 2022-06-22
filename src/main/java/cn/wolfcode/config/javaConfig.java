package cn.wolfcode.config;

import cn.wolfcode.interceptor.LoginInterceptor;
import cn.wolfcode.interceptor.PermissionInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class javaConfig  implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private PermissionInter permissionInter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/static/**","/login","/logout");
        registry.addInterceptor(permissionInter)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/static/**","/login","/logout");

    }
}
