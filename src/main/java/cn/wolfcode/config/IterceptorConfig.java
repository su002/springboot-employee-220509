package cn.wolfcode.config;

import cn.wolfcode.interceptor.InterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class IterceptorConfig implements WebMvcConfigurer {
    @Autowired
    InterConfig InterceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interConfig = registry.addInterceptor(InterceptorConfig);
        interConfig.addPathPatterns("/**")
                .excludePathPatterns("/static/login.html","/static/**","/login","/logout");

    }
}
