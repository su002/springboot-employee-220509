package cn.wolfcode.service.impl;


import cn.wolfcode.domain.Employee;
import cn.wolfcode.mapper.UserMapper;
import cn.wolfcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapperImpl;

    @Override
    public Employee selectUser(String username, String password) {
        Employee user = userMapperImpl.selectUser(username, password);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public String selectName(String username) {
        return userMapperImpl.selectName(username);
    }

    @Override
    public Employee selectCountAndName(String username) {
        return userMapperImpl.selectCountAndName(username);
    }

    @Override
    public void updateCount(String username) {
        userMapperImpl.updateCount(username);
    }

    @Override
    public void updateStatus(String username) {
        userMapperImpl.updateStatus(username);
    }
}
