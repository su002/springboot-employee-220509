package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapperImpl;


    @Override
    public void saveOrUpdate(Department department) {
        Long id = department.getId();
        if (id == null){
            //添加
            departmentMapperImpl.save(department);
        }else{
            //更新
            departmentMapperImpl.update(department);
        }
    }


    @Override
    public void deleteById(Long id) {
        departmentMapperImpl.delete(id);
    }


    @Override
    public Department selectOne(Long id) {
        return departmentMapperImpl.selectOne(id);
    }


    @Override
    public List<Department> selectAll() {
        return departmentMapperImpl.selectAll();
    }


    @Override
    public PageInfo<Department> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Department> departments = departmentMapperImpl.selectAll();
        return new PageInfo<>(departments);

    }

    @Override
    public void insertRole( String expression) {
        departmentMapperImpl.insertRole(expression);
    }

    @Override
    public List<String> selectRoleAll() {
        return departmentMapperImpl.selectRoleAll();
    }

    @Override
    public void updateRole(String name, String permission) {
        departmentMapperImpl.updateRole(name , permission);
    }
}
