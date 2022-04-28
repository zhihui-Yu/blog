package xyz.yzh.blogweb.domian;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.yzh.blogweb.utils.TimeConstants;

import java.time.ZonedDateTime;

@Data
public class SuperDomain<T extends Model> extends Model {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String createdBy;

    @DateTimeFormat(pattern = TimeConstants.TIME_FORMAT)
    @JsonFormat(pattern = TimeConstants.TIME_FORMAT)
    @TableField(fill = FieldFill.INSERT)
    public ZonedDateTime createdTime;

    private String updatedBy;

    /**
     * 不同的 json 工具 对比
     * 1. jackson   只支持date，其他类型都要再配置，private属性不转换
     * 2. gson      ZoneDateTime 显示多重结构， private属性显示
     * 3. fastJson  Date 显示时间戳，private属性不显示
     */
    @DateTimeFormat(pattern = TimeConstants.TIME_FORMAT)
    @JsonFormat(pattern = TimeConstants.TIME_FORMAT)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public ZonedDateTime updatedTime;
}
