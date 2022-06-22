package cn.wolfcode.service;

import cn.wolfcode.domain.Course;

import cn.wolfcode.domain.Student;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    void saveOrUpdate(Course course);

    void delete(Long id);

    Course selectOne(Long id);

    List<Course> selectAll();

    PageInfo<Course> query(QueryObject queryObject);

    List<Course> selectRoleByEmpId(Long id);
    Integer  StudentNum(Long id);
    PageInfo<Student> selectCourseAndStudents(Long id ,QueryObject queryObject);
}
