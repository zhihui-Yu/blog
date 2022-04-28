package xyz.yzh.blogweb.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author simple
 */
//@Configuration
//@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogInterceptor.class);
    // 可以使用切面编程
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ip: " + request.getRemoteAddr());
        log.info("host: " + request.getRemoteHost());
        log.info("path: " + request.getMethod() + " " + request.getRequestURI());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
