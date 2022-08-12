package xyz.yzh.blogweb.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author simple
 */
@Component
public class CorsInterceptor implements HandlerInterceptor {
    //在controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "content-type,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //允许加入的自定义响应头
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.addHeader("token", "token123456abc");
        return true;//返回true放行 返回falser 拦截
    }
}

