package cn.wolfcode.service.impl;


import cn.wolfcode.domain.Role;
import cn.wolfcode.mapper.RoleMapper;

import cn.wolfcode.query.QueryObject;

import cn.wolfcode.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Role> roles = roleMapper.selectAll();
        return new PageInfo<>(roles);
    }

    @Override
    public List<Role> selectRoleByEmpId(Long id) {
        return roleMapper.selectRoleByEmpId(id);
    }

    @Override
    public Role selectOne(Long id) {
        return roleMapper.selectOne(id);
    }

    @Override
    public void saveOrUpdate(Role role, Long[] permissionIds) {
        if (role.getId() != null) {
            roleMapper.update(role);
        } else {
            roleMapper.save(role);
        }

        roleMapper.deleteRoleAndPermissionRelation(role.getId());
        if (permissionIds != null) {
            for (Long permissionId : permissionIds) {
                roleMapper.saveRoleAndPermission(role.getId(), permissionId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        roleMapper.delete(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }


}
