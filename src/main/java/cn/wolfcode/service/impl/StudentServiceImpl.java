package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.Student;
import cn.wolfcode.mapper.StudentMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService {

    @Autowired
    private StudentMapper studentMapper ;
    @Override
    public void saveOrUpdate(Student student,Long[] permissionIds) {
        if (student.getId() != null) {
            studentMapper.update(student);
        }else {
            studentMapper.save(student);
        }
        studentMapper.deleteRoleAndPermissionRelation(student.getId());
        if (permissionIds.length != 0) {
            for (Long permissionId : permissionIds) {
                studentMapper.saveRoleAndPermission(student.getId(), permissionId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        studentMapper.delete(id);
    }

    @Override
    public Student selectOne(Long id) {
        return studentMapper.selectOne(id);
    }

    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @Override
    public PageInfo<Student> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Student> employees = studentMapper.selectAll();
        return new PageInfo<>(employees);

    }

    @Override
    public List<Student> selectRoleByEmpId(Long id) {
        return studentMapper.selectRoleByEmpId(id);
    }
}
