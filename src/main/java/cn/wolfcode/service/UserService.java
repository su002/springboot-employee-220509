package cn.wolfcode.service;


import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.User;

public interface UserService {
    Employee selectUser(String username, String password);

    String selectName(String username);

    Employee selectCountAndName(String username);

    void updateCount(String username);

    void updateStatus(String username);
}
