package cn.wolfcode.mapper;

import cn.wolfcode.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    void save(Student student);

    void delete(Long id);

    void update(Student student);

    Student selectOne(Long id);

    List<Student> selectAll();

    int queryForCount();


    void saveRoleAndPermission(@Param("student_id") Long id, @Param("permissionId") Long permissionId);

    void deleteRoleAndPermissionRelation(Long id);

    //根据员工Id查角色
    List<Student> selectRoleByEmpId(Long id);
}
