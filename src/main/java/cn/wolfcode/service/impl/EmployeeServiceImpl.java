package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.EmployeeQueryObject;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.service.EmployeeService;
import cn.wolfcode.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapperImpl;
    @Autowired
    private RoleService roleServiceImpl;


    @Override
    public void saveOrUpdate(Employee employee,Role role) {
        Long id = employee.getId();
        if (id == null){
            //添加
            employeeMapperImpl.save(employee);
        }else {
            //更新
            employeeMapperImpl.update(employee);
        }
    }

    @Override
    public Employee selectOne(Long id) {
        return employeeMapperImpl.selectOne(id);
    }

    @Override
    public void deleteById(Long id) {
        employeeMapperImpl.delete(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapperImpl.selectAll();
    }

    @Override
    public PageInfo<Employee> query(EmployeeQueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Employee> employees = employeeMapperImpl.selectAll();
        return new PageInfo<>(employees);
    }

    @Override
    public void batchDeleteById(List<Long> empIds) {
        employeeMapperImpl.batchDeleteById(empIds);
    }
}
