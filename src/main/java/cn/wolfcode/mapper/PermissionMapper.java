package cn.wolfcode.mapper;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Li hairui
 * @CreateTime: 2022/4/6 14:29
 */
@Repository
public interface PermissionMapper {
    int queryForCount();


    void delete(Long id);

    List<Permission> selectAll();

    void deleteAll();

    void save(Permission permission);

    void deletePermissionAndRoleRelation(Long id);

    void batchSave(@Param("newPermissions") List<Permission> newPermissions);
    //void batchSave(List<Permission> newPermissions);

    List<Permission> selectPermissionByRoleId(Long id);

    List<String> selectByEmployeeId(Long id);
}
