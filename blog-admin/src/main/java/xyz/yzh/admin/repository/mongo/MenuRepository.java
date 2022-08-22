package xyz.yzh.admin.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.yzh.admin.domain.Menu;

/**
 * @author simple
 */
public interface MenuRepository extends MongoRepository<Menu, String> {
}
