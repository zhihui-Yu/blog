package xyz.yzh.admin.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.yzh.admin.domain.BlogContent;
import xyz.yzh.admin.service.BlogContentService;

import java.util.List;

/**
 * @author simple
 */
@RestController
@RequestMapping("/blog-content")
public class BlogContentController {
    @Autowired
    private BlogContentService blogContentService;

    @PostMapping("/")
    public void save(@RequestBody BlogContent blogContent) {
        blogContentService.save(blogContent);
    }

    @GetMapping("/")
    @ApiOperation(value = "搜索所有文档")
    public List<BlogContent> list() {
        return blogContentService.list();
    }

    @DeleteMapping("/")
    @ApiOperation("删除所有文档")
    public void deleteAll() {
        blogContentService.deleteAll();
    }
}
