package xyz.yzh.blogweb.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import xyz.yzh.blogweb.vo.R;

/**
 * @author simple
 */
@Service
public class ResultUtils {
//    private final static ObjectMapper MAPPER = new ObjectMapper(); // jackson
//    private final static Gson gson = new GsonBuilder().setDateFormat(TimeConstants.TIME_FORMAT).create(); // gson

    public static <T> String toJson(T data) throws JsonProcessingException {
        return JSON.toJSONString(new R<>().ofSuccess(data));
//        return gson.toJson(new R<>().ofSuccess(data));
//        return MAPPER.writeValueAsString(new R<>().ofSuccess(data));
    }

//    public static <T> T j2o(String json, Class<T> clz) throws JsonProcessingException {
//        return MAPPER.readValue(json, clz);
//    }
}
