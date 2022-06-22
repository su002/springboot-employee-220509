package cn.wolfcode.config;


import cn.wolfcode.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*@Configuration
@ComponentScan(basePackages = {"cn.wolfcode.interceptor"})*/
public class InterceptorConfiguer implements WebMvcConfigurer {
  /*  @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(loginInterceptor);
        //拦截所有路径
        interceptor.addPathPatterns("/**");
        //不拦截路径
        interceptor.excludePathPatterns("/user/**");
        interceptor.excludePathPatterns("/login");
        interceptor.excludePathPatterns("/mallSystem");
        interceptor.excludePathPatterns("/userLogin");
        interceptor.excludePathPatterns("/login.html");
        interceptor.excludePathPatterns("/login.jsp");
        interceptor.excludePathPatterns("/css/**");
        interceptor.excludePathPatterns("/js/**");
    }
*/
}