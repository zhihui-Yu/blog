package xyz.yzh.blogweb.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author simple
 */
//@Component
//@FeignClient(value = "redis-service", fallback = RedisServiceFallback.class, decode404 = true, path = "/redis")
public interface RedisService {
    @PutMapping("string")
    void putKey(@RequestBody Map<String, String> map);

    @GetMapping("string/{key}")
    String getKey(@PathVariable String key);
}
