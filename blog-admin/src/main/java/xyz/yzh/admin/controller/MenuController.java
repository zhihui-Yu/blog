package xyz.yzh.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.admin.domain.Menu;
import xyz.yzh.admin.service.MenuService;

/**
 * @author simple
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/")
    public void createMenu(@RequestBody Menu menu) {
        menuService.create(menu);
    }
}
