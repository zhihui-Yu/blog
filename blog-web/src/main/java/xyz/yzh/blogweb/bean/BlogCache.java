package xyz.yzh.blogweb.bean;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import xyz.yzh.blogweb.utils.FileUtils;
import xyz.yzh.blogweb.utils.SnowFlake;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author simple
 */
@Configuration
public class BlogCache {
    private final static String DIR = "/blog/summary";
    private final static Set<BlogFile> blogFiles = new HashSet<>();
    private final static Map<Long, String> pathMap = new HashMap<>();
    private final static Map<String, Long> reversePathMap = new HashMap<>();

    public static void main(String[] args) {
        new BlogCache();
    }

    public BlogCache() {
        System.out.println("initial blog cache, => " + this);
        init();
    }

    @Bean
    public BlogCache cache() {
        return new BlogCache();
    }

    private void init() {
        var file = new File(DIR);
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
            return "BlogFile{" + "subFiles=" + subFiles + ", isDirectory=" + isDirectory + ", relativePath='" + relativePath + '\'' + '}';
        }
    }
}
