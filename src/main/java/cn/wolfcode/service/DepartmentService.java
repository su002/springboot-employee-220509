package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DepartmentService {

    void saveOrUpdate(Department department);

    void deleteById(Long id);

    Department selectOne(Long id);

    List<Department> selectAll();

    //分页查询
    PageInfo<Department> query(QueryObject queryObject);

    void insertRole(String expression);

    List<String> selectRoleAll();


    void updateRole(String name, String permission);
}
