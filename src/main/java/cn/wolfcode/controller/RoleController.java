package cn.wolfcode.controller;


import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.PermissionService;
import cn.wolfcode.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleServiceImpl;

    @Autowired
    private PermissionService permissionServiceImpl;

    @RequestMapping("/list")
    public String list(Model model,@Param("queryObject") QueryObject queryObject) {
        PageInfo pageInfo = roleServiceImpl.query(queryObject);
        model.addAttribute("pageInfo", pageInfo);
        return "role/list";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {
        List<Permission> result = permissionServiceImpl.selectAll();
        model.addAttribute("permissions",result);
        if (id != null) {
            //数据回显
            Role role = roleServiceImpl.selectOne(id);
            model.addAttribute("role", role);
           List<Permission> ownPermissions = permissionServiceImpl.selectPermissionByRoleId(id);
           model.addAttribute("ownPermissions",ownPermissions);
        }

        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role,Long[] permissionIds) {
        roleServiceImpl.saveOrUpdate(role,permissionIds);
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        roleServiceImpl.delete(id);
        return "redirect:/role/list";
    }
}
