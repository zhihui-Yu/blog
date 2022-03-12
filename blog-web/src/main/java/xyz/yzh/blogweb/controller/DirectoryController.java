package xyz.yzh.blogweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.blogweb.utils.ResultUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author simple
 */
@RestController
@RequestMapping("/api")
public class DirectoryController {

    @GetMapping("/directory")
    public String directory() {
        System.out.println("============= directory ==================");
        Dir dir = new Dir("1","level 1");

        Dir dir1 = new Dir("1","level 1");
        dir1.children.add(new Dir("3", "level 1-1"));
        dir1.children.add(new Dir("3", "level 1-2"));
        dir1.children.add(new Dir("3", "level 1-3"));

        Dir dir2 = new Dir("1","level 2");
        dir2.children.add(new Dir("3", "level 2-1"));
        dir2.children.add(new Dir("3", "level 2-2"));
        dir2.children.add(new Dir("3", "level 2-3"));

        dir.children.add(dir1);
        dir.children.add(dir2);
        return ResultUtils.toJson(List.of(dir));
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
