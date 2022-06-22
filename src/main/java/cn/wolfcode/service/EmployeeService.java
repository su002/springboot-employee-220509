package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.EmployeeQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface EmployeeService {

    void saveOrUpdate(Employee employee,Role role);

    void deleteById(Long id);

    Employee selectOne(Long id);

    List<Employee> selectAll();
    //分页查询数据
    PageInfo<Employee> query(EmployeeQueryObject queryObject);

    void batchDeleteById(List<Long> empIds);

}
