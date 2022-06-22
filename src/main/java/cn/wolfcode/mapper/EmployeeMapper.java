package cn.wolfcode.mapper;


import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {

  void save(Employee employee);

  void update(Employee employee);

  void delete(Long id);

  Employee selectOne(Long id);

  List<Employee> selectAll();

  int selectForCount();


  //批量删除
  void batchDeleteById(@Param("emIds") List<Long> empIds);

  //删除employee_role中间表信息
  void deleteEmployeeAndEmpRole(Long id);
  //保存中间表employee_role信息
  void saveEmployeeAndRole(@Param("employee") Employee employee,@Param("roles") Role role);


}
