package xyz.yzh.blogweb.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.yzh.blogweb.utils.FileUtils;
import xyz.yzh.blogweb.utils.SnowFlake;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author simple
 */
@Configuration
public class BlogCache {
    private final static String DIR = "static" + FileUtils.separator + "summary";
    private final static Set<BlogFile> blogFiles = new HashSet<>();
    private final static Map<Long, String> pathMap = new HashMap<>();
    private final static Map<String, Long> reversePathMap = new HashMap<>();

    @Bean
    public BlogCache cache() {
        return new BlogCache();
    }

    public BlogCache() {
        System.out.println("initial blog cache, => " + this);
        init();
    }

    private void init() {
        // 项目根路径， 但是window下会在开头加入 '/'
        var baseDir = Objects.requireNonNull(BlogCache.class.getClassLoader().getResource(DIR)).getPath();
        var file = new File(baseDir);
        initialCache(file);
    }

    private void initialCache(File file) {
        if (file.isDirectory() && file.listFiles() != null) {
            for (var subFile : Objects.requireNonNull(file.listFiles())) {
                File[] subFiles = subFile.listFiles();
                blogFiles.add(new BlogFile(subFiles(subFiles), Objects.nonNull(subFiles), getPath(file)));
            }
        }
        System.out.println(JSON.toJSON(blogFiles));
        System.out.println(JSON.toJSON(DIR));
        System.out.println(JSON.toJSON(File.separator));
    }

    private List<BlogFile> subFiles(File[] subFiles) {
        if (subFiles == null || subFiles.length == 0) return List.of();

        return Stream.of(subFiles).map(file -> {
            File[] files = file.listFiles();
            var blogFile = new BlogFile(subFiles(files), Objects.nonNull(files), getPath(file));
            if (!blogFile.isDirectory) {
                long id = SnowFlake.GENERATOR.nextId();
                pathMap.put(id, blogFile.relativePath);
                reversePathMap.put(blogFile.relativePath, id);
            }
            return blogFile;
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        new BlogCache();
    }

    public Set<BlogFile> getBlogFiles() {
        return blogFiles;
    }

    public String getPath(Long id) {
        return pathMap.get(id);
    }

    public Long getId(String path) {
        return reversePathMap.get(path);
    }

    private String getPath(File file) {
        String path = file.getPath();
        return path.substring(path.indexOf("static")).replaceAll("\\\\", FileUtils.separator);
    }

    public static class BlogFile {
        public List<BlogFile> subFiles;
        public Boolean isDirectory;
        public String relativePath;

        public BlogFile(List<BlogFile> subFiles, Boolean isDirectory, String relativePath) {
            this.subFiles = subFiles;
            this.isDirectory = isDirectory;
            this.relativePath = relativePath;
        }

        @Override
        public String toString() {
            return "BlogFile{" +
                "subFiles=" + subFiles +
                ", isDirectory=" + isDirectory +
                ", relativePath='" + relativePath + '\'' +
                '}';
        }
    }
}
