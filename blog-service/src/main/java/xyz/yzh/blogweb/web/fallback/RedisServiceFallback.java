package xyz.yzh.blogweb.web.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.yzh.blogweb.web.RedisService;

import java.util.Map;

/**
 * @author simple
 */
@Slf4j
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public void putKey(Map<String, String> map) {
        log.error("put key to redis failed.");
    }

    @Override
    public String getKey(String key) {
        log.error("get key from redis failed.");
        return null;
    }
}
