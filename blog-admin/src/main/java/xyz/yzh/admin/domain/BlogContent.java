package xyz.yzh.admin.domain;

/**
 * @author simple
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serial;
import java.io.Serializable;

@Document(indexName = "blog_content")
public class BlogContent implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    public String blogId;

    @Field(name = "title")
    public String title;

    @Field(name = "content", type = FieldType.Keyword) // analyzer = "ik_max_word"
    public String content;

    @Field(name = "created_at", type = FieldType.Date)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Long createdAt;

    // todo 存储为时间格式 而不是时间戳
    @Field(name = "updated_at", type = FieldType.Date)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Long updatedAt;
}
