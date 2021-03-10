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
    public void insertUser(String name,String token) {
        User user = new User();
        user.setAccountId(UUID.randomUUID().toString());
        user.setName(name);
        user.setToken(token);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        userMapper.insertUser(user);
    }
}
