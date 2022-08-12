package xyz.yzh.blogweb.bean;

import org.springframework.stereotype.Component;
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
@Component
public class BlogCache {
    private final static String DIR = "C:\\Users\\simple\\IdeaProjects\\Trivia\\summary";
    private final static List<String> fileEnd = List.of(".md", ".txt", ".yaml", ".yml");
    private final static Set<BlogFile> blogFiles = new HashSet<>();
    private final static Map<String, String> pathMap = new HashMap<>();
    private final static Map<String, String> reversePathMap = new HashMap<>();

    public BlogCache() {
        System.out.println("initial blog cache, => " + this);
        init();
        System.out.println("blog file size: " + blogFiles.size());
    }

    public void init() {
        if (!blogFiles.isEmpty()) blogFiles.clear();
        if (!pathMap.isEmpty()) pathMap.clear();
        if (!reversePathMap.isEmpty()) reversePathMap.clear();

        var file = new File(DIR);
        if (file.exists()) {
            initialCache(file);
        }
    }

    private void initialCache(File file) {
        if (file.isDirectory() && file.listFiles() != null) {
            for (var subFile : Objects.requireNonNull(file.listFiles())) {
                File[] subFiles = subFile.listFiles();
                BlogFile blogFile = new BlogFile(subFiles(subFiles), Objects.nonNull(subFiles), getPath(subFile));
                if (blogFile.isDirectory && !blogFile.subFiles.isEmpty()) {
                    blogFiles.add(blogFile);
                }
            }
        }
    }

    private List<BlogFile> subFiles(File[] subFiles) {
        if (subFiles == null || subFiles.length == 0) return List.of();

        return Stream.of(subFiles).map(file -> {
            if (!file.isDirectory() && fileEnd.stream().noneMatch(x -> file.getAbsolutePath().contains(x))) {
                return null;
            }
            File[] files = file.listFiles();
            var blogFile = new BlogFile(subFiles(files), Objects.nonNull(files), getPath(file));
            if (!blogFile.isDirectory) {
                long id = SnowFlake.GENERATOR.nextId();
                pathMap.put(String.valueOf(id), blogFile.absolutePath);
                reversePathMap.put(blogFile.absolutePath, String.valueOf(id));
            }
            return blogFile;
        }).filter(Objects::nonNull).filter(f -> !(f.isDirectory && f.subFiles.isEmpty())).collect(Collectors.toList());
    }

    public Set<BlogFile> getBlogFiles() {
        return blogFiles;
    }

    public String getPath(String id) {
        return pathMap.get(id);
    }

    public String getId(String path) {
        return reversePathMap.get(path);
    }

    private String getPath(File file) {
        return file.getAbsolutePath().replaceAll("\\\\", "/");
    }

    public static class BlogFile {
        public List<BlogFile> subFiles;
        public Boolean isDirectory;
        public String absolutePath;

        public BlogFile(List<BlogFile> subFiles, Boolean isDirectory, String absolutePath) {
            this.subFiles = subFiles;
            this.isDirectory = isDirectory;
            this.absolutePath = absolutePath;
        }

        @Override
        public String toString() {
            return "BlogFile{" + "subFiles=" + subFiles + ", isDirectory=" + isDirectory + ", relativePath='" + absolutePath + '\'' + '}';
        }
    }
}
