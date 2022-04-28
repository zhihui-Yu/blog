package xyz.yzh.blogweb.domian;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class User extends SuperDomain<User> {
    private String name;
    private String email;
    private String avatarLink;
    private String githubLink;
}
