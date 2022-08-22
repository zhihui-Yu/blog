package xyz.yzh.admin.domain;

import lombok.Data;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

/**
 * @author simple
 */
@Data
@Document("menu")
public class Menu implements Persistable<String> {
    @MongoId
    public String id;

    @Field(name = "name")
    public String name;

    @Field(name = "sub_menu")
    public List<Menu> subMenu;

    @Field(name = "created_at")
    public Date createdAt;

    @Field(name = "updated_at")
    public Date updatedAt;

    /**
     * 使用 Persistable， 是因为jpa使用save，即使主键重复也不会抛异常。
     * 原理： 先判断主键是否存在，不存在插入，存在无作为
     * 如果判断new=true，则会在DB上执行操作，从而抛出主键重复异常，否则会不执行语句
     */
    @Override
    public boolean isNew() {
        return true;
    }
}
