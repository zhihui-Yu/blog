package xyz.yzh.blogweb.service.impl;

import org.springframework.stereotype.Service;
import xyz.yzh.blogweb.domian.User;
import xyz.yzh.blogweb.mapper.UserMapper;
import xyz.yzh.blogweb.service.UserService;

import javax.annotation.Resource;

/**
 * @author simple
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User findMe() {
        return userMapper.selectById(0);
    }
}
