package xyz.yzh.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yzh.admin.domain.Menu;
import xyz.yzh.admin.repository.mongo.MenuRepository;

/**
 * @author simple
 */
@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public void create(Menu menu) {
        // 如果id 重复，则抛异常 / 包装一层
        menuRepository.save(menu);
    }
}
