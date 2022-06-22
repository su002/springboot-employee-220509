package cn.wolfcode.service;


import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.domain.Permission;

import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Description:
 * @Author: Li hairui
 * @CreateTime: 2022/4/6 14:38
 */
public interface PermissionService {

    PageInfo<Permission> query(QueryObject queryObject);

    void delete(Long id);

    List<Permission> selectAll();

    void reload();

    List<Permission> selectPermissionByRoleId(Long id);

    List<String> selectByEmployeeId(Long id);

}
