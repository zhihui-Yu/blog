package xyz.yzh.blogweb.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("blogs")
public class Blog extends SuperDomain<Blog> {
    private String name;

    private String tags;

    private String title;

    private Integer viewCount;
}
