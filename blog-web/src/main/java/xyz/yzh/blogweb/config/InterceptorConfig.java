package xyz.yzh.blogweb.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.yzh.blogweb.interceptor.CorsInterceptor;

import javax.annotation.Resource;

/**
 * @author simple
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    CorsInterceptor corsInterceptor;

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义拦截器
        registry.addInterceptor(corsInterceptor)
            //拦截器拦截那些路径
            .addPathPatterns("/api/**");
        //不拦截的路径
        // .excludePathPatterns("");
    }
}
