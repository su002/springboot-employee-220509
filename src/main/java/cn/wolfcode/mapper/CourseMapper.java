package cn.wolfcode.mapper;


import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    void save(Course role);

    void delete(Long id);

    void update(Course role);

    Course selectOne(Long id);

    List<Course> selectAll();

    int queryForCount();


    void saveRoleAndPermission(@Param("roleId") Long id, @Param("permissionId") Long permissionId);

    void deleteRoleAndPermissionRelation(Long id);

    //根据员工Id查角色
//    List<Role> selectRoleByEmpId(Long id);
    Integer  selectStudentNum(Long id);

    List<Student> selectCourseAndStudents(Long id);

    List<Course> selectCount(@Param("courses") List<Course> courses);
}
