package xyz.yzh.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yzh.admin.domain.BlogContent;
import xyz.yzh.admin.repository.elasticsearch.BlogContentRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author simple
 */
@Service
public class BlogContentService {
    @Autowired
    private BlogContentRepository blogContentRepository;

    public void save(BlogContent blogContent) {
        blogContentRepository.save(blogContent);
    }

    public List<BlogContent> list() {
        List<BlogContent> list = new LinkedList<>();
        blogContentRepository.findAll().forEach(list::add);
        return list;
    }

    public void deleteAll() {
        blogContentRepository.deleteAll();
    }
}
