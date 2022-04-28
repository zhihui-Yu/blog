package xyz.yzh.blogweb.vo;

import com.google.j2objc.annotations.Property;

import static xyz.yzh.blogweb.vo.R.Result.ERROR;
import static xyz.yzh.blogweb.vo.R.Result.SUCCESS;

/**
 * @author simple
 */
public class R<T> {
    public T data;
    public Result result;
    public String errorMsg;

    public R<T> ofSuccess(T data) {
        this.data = data;
        this.result = SUCCESS;
        return this;
    }

    public R<T> ofError(String errorMsg) {
        this.result = ERROR;
        this.errorMsg = errorMsg;
        return this;
    }

    public enum Result {
        @Property("SUCCESS")
        SUCCESS,
        @Property("ERROR")
        ERROR
    }
}
