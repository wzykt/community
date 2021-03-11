package wzy.com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wzy.com.demo.mapper.UserMapper;
import wzy.com.demo.pojo.User;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User insertUser(String name,String id) {
        User user = new User();
        user.setAccountId(id);
        user.setName(name);
        user.setToken(UUID.randomUUID().toString());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        userMapper.insertUser(user);
        return user;
    }
}
