package cn.wolfcode.mapper;


import cn.wolfcode.query.QueryObject;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    void save(Role role);

    void delete(Long id);

    void update(Role role);

    Role selectOne(Long id);

    List<Role> selectAll();

    int queryForCount();


    void saveRoleAndPermission(@Param("roleId") Long id, @Param("permissionId") Long permissionId);

    void deleteRoleAndPermissionRelation(Long id);

    //根据员工Id查角色
    List<Role> selectRoleByEmpId(Long id);
}
