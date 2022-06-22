package cn.wolfcode.mapper;


import cn.wolfcode.domain.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface UserMapper {
    Employee selectUser(@Param("username") String username, @Param("password") String password);

    String selectName(String username);

    Employee selectCountAndName(String username);

    void updateCount(String username);

    void updateStatus(String username);
}
