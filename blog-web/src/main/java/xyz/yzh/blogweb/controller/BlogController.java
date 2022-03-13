package xyz.yzh.blogweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.blogweb.utils.FileReadUtils;
import xyz.yzh.blogweb.utils.ResultUtils;

import java.io.IOException;

/**
 * @author simple
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @GetMapping("/{id}")
    public String getBlog(@PathVariable("id") String id) throws IOException {
//        String s = MarkDown2HtmlUtils.markdown2Html(FileReadUtils.read("static/锁.md"));
        return ResultUtils.toJson(FileReadUtils.read("static/锁.md"));
    }
}
