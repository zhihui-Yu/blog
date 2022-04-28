package xyz.yzh.blogweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.blogweb.bean.BlogCache;
import xyz.yzh.blogweb.utils.FileUtils;
import xyz.yzh.blogweb.utils.ResultUtils;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author simple
 */
@RestController
public class DirectoryController {
    @Resource
    BlogCache blogCache;

    @GetMapping("/directory")
    public String directory() {
        var data = blogCache.getBlogFiles().stream().map(blogFile -> {
            var dir = new Dir(
                blogCache.getId(blogFile.absolutePath),
                blogFile.absolutePath.toLowerCase(Locale.ROOT).substring(blogFile.absolutePath.lastIndexOf(FileUtils.separator) + 1)
            );
            dir.children.addAll(children(blogFile.subFiles));
            return dir;
        }).sorted(Comparator.comparing(x -> x.name)).collect(Collectors.toList());
        return ResultUtils.toJson(data);
    }

    public List<Dir> children(List<BlogCache.BlogFile> subFiles) {
        return subFiles.stream().map(file -> {
            var dir = new Dir(
                blogCache.getId(file.absolutePath),
                file.absolutePath.toLowerCase(Locale.ROOT).substring(file.absolutePath.lastIndexOf("/") + 1)
            );
            dir.children.addAll(children(file.subFiles));
            return dir;
        }).collect(Collectors.toList());
    }

    public static class Dir {
        public String id;
        public String name;
        public List<Dir> children = new LinkedList<>();

        public Dir(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
