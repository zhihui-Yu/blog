package xyz.yzh.blogweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.blogweb.web.RedisService;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Resource
    RedisService redisService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/get/{key}")
    public String getKey(@PathVariable String key) {
        return redisService.getKey(key);
    }
}
