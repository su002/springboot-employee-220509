package cn.wolfcode.service;

import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.Student;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {

    void saveOrUpdate(Student student,Long[] permissionIds);

    void delete(Long id);

    Student selectOne(Long id);

    List<Student> selectAll();

    PageInfo<Student> query(QueryObject queryObject);

    List<Student> selectRoleByEmpId(Long id);

//    void deleteById(Long id);
}
