package cn.wolfcode.service;


import cn.wolfcode.domain.Role;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    void saveOrUpdate(Role role, Long[] permissionIds);

    void delete(Long id);

    Role selectOne(Long id);

    List<Role> selectAll();

    PageInfo<Role> query(QueryObject queryObject);

    List<Role> selectRoleByEmpId(Long id);
}
