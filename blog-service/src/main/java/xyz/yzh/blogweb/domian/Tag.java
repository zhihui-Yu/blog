package xyz.yzh.blogweb.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tags")
public class Tag extends SuperDomain<Tag> {
    public String name;
}
