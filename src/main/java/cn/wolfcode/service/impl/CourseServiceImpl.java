package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Student;
import cn.wolfcode.mapper.CourseMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.CourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl  implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Override
    public void saveOrUpdate(Course course) {
//        if (course.getId() != null) {
//            courseMapper.update(course);
//        } else {
//            courseMapper.save(course);
//        }
//        courseMapper.deleteRoleAndPermissionRelation(course.getId());
//        if (permissionIds != null) {
//            for (Long permissionId : permissionIds) {
//                courseMapper.saveRoleAndPermission(course.getId(), permissionId);
//            }
//        }

        Long id = course.getId();
        if (id == null){
            //添加
            courseMapper.save(course);
        }else{
            //更新
            courseMapper.update(course);
        }
    }

    @Override
    public void delete(Long id) {
        courseMapper.delete(id);
    }

    @Override
    public Course selectOne(Long id) {
        return courseMapper.selectOne(id);
    }

    @Override
    public List<Course> selectAll() {
        return courseMapper.selectAll();
    }

    @Override
    public PageInfo<Course> query(QueryObject queryObject) {

        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());

        List<Course> courses = courseMapper.selectAll();
        List<Course> count = courseMapper.selectCount(courses);
        Map<Long, Integer> map = new HashMap<>();
        for (Course course : count) {
            Long courseid = course.getId();
            if (!map.containsKey(courseid)) {
                Integer num = course.getNum();
                map.put(courseid, num);
            }
        }
        for (Course course : courses) {
            Integer integer = map.get(course.getId());
            course.setNum(integer);
        }


        return new PageInfo<>(courses);
    }


    @Override
    public List<Course> selectRoleByEmpId(Long id) {
        return null;
    }


    @Override
    public Integer StudentNum(Long id) {
        return courseMapper.selectStudentNum(id);
    }

    @Override
    public PageInfo<Student> selectCourseAndStudents(Long id , QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Student> studentList = courseMapper.selectCourseAndStudents(id);
        return new PageInfo<>(studentList);
    }
}
