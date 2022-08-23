package xyz.yzh.admin.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import xyz.yzh.admin.domain.BlogContent;

/**
 * @author simple
 */
public interface BlogContentRepository extends ElasticsearchRepository<BlogContent, String> {
}
