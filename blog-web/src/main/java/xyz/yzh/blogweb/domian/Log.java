package xyz.yzh.blogweb.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author simple
 */
@Data
@TableName("logs")
public class Log extends SuperDomain<Log> {
    private String ip;
    private String host;
    private String method;
    private String requestURI;
}
