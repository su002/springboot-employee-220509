package cn.wolfcode.mapper;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    void save(Department department);

    void update(Department department);

    void delete(Long id);

    Department selectOne(Long id);

    List<Department> selectAll();

    int selectForCount();


    void insertRole(String expression);

    List<String> selectRoleAll();

    void updateRole(String name, String permission);
}
