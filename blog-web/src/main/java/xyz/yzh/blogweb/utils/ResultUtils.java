package xyz.yzh.blogweb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import xyz.yzh.blogweb.vo.R;

/**
 * @author simple
 */
@Service
public class ResultUtils {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String toJson(T data) throws JsonProcessingException {
        return MAPPER.writeValueAsString(new R<>().ofSuccess(data));
    }

    public static <T> T j2o(String json, Class<T> clz) throws JsonProcessingException {
        return MAPPER.readValue(json, clz);
    }
}
