package xyz.yzh.blogweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.blogweb.bean.BlogCache;
import xyz.yzh.blogweb.utils.FileUtils;
import xyz.yzh.blogweb.utils.ResultUtils;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author simple
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    BlogCache blogCache;

    @GetMapping("/{id}")
    public String getBlog(@PathVariable("id") String id) throws IOException {
//        String s = MarkDown2HtmlUtils.markdown2Html(FileReadUtils.read("static/锁.md"));
        return ResultUtils.toJson(FileUtils.read(blogCache.getPath(id)));
    }

    @PutMapping("/refresh")
    public void refresh() {
        blogCache.init();
    }
}
