package xyz.yzh.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author simple
 */
@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @PutMapping("string")
    public void putKey(@RequestBody Map<String, String> map) {
        map.forEach((k, v) -> stringRedisTemplate.opsForValue().set(k, v));
    }

    @GetMapping("string/{key}")
    public String getKey(@PathVariable String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
