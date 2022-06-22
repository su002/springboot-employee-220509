package cn.wolfcode.service.impl;


import cn.wolfcode.domain.Permission;
import cn.wolfcode.mapper.PermissionMapper;

import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description:
 * @Author: Li hairui
 * @CreateTime: 2022/4/6 14:38
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public PageInfo<Permission> query(QueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(),queryObject.getPageSize());
        List<Permission> permissions = permissionMapper.selectAll();
        return new PageInfo<>(permissions);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deletePermissionAndRoleRelation(id);
        permissionMapper.delete(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }



    @Override
    public List<Permission> selectPermissionByRoleId(Long id) {
        return permissionMapper.selectPermissionByRoleId(id);
    }

    @Override
    public List<String> selectByEmployeeId(Long id) {

        return permissionMapper.selectByEmployeeId(id);
    }


    @Override
    public void reload() {

    }

}
