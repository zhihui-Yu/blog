package xyz.yzh.blogweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yzh.blogweb.domian.User;
import xyz.yzh.blogweb.service.UserService;
import xyz.yzh.blogweb.utils.ResultUtils;

import javax.annotation.Resource;

/**
 * @author simple
 */
@RestController
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/about-me")
    public String aboutMe() {
        User me = userService.findMe();
        return ResultUtils.toJson(me);
    }
}
