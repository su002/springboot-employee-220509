package cn.wolfcode.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.noon.RequirePremission;
import cn.wolfcode.query.EmployeeQueryObject;
import cn.wolfcode.service.DepartmentService;
import cn.wolfcode.service.EmployeeService;
import cn.wolfcode.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeServiceImpl;
    @Autowired
    private DepartmentService departmentServiceImpl;
    @Autowired
    private RoleService roleServiceImpl;


    @RequestMapping("/list")
    public String list(@ModelAttribute("queryObject") EmployeeQueryObject queryObject, Model model) {
        PageInfo pageInfo = employeeServiceImpl.query(queryObject);
        List<Department> departments = departmentServiceImpl.selectAll();
        model.addAttribute("departments",departments);
        model.addAttribute("pageInfo", pageInfo);
        return "employee/list";
    }

    @RequirePremission(name = "保存或修改001" , expression = "saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Employee employee,Role role) {
        employeeServiceImpl.saveOrUpdate(employee,role);
        return "redirect:/employee/list";
    }

    @RequirePremission(name = "删除" , expression = "delete")
    @RequestMapping("/delete")
    public String deleteDepartment(Long id) {
        employeeServiceImpl.deleteById(id);
        return "redirect:/employee/list";
    }

    @RequestMapping("/batchDelete")
    public String deleteDepartment(List<Long> empId) {
        employeeServiceImpl.batchDeleteById(empId);
        return "redirect:/employee/list";
    }


    @RequestMapping("/input")
    public String input(Long id, Model model) {
        List<Department> department = departmentServiceImpl.selectAll();
        List<Role> roles = roleServiceImpl.selectAll();
        List<Role> ownRoles = roleServiceImpl.selectRoleByEmpId(id);
        model.addAttribute("ownRoles",ownRoles);
        model.addAttribute("roles",roles);
        model.addAttribute("departments",department);
        if (id != null) {
           Employee employee = employeeServiceImpl.selectOne(id);
            model.addAttribute("employee", employee);
        }
        return "employee/input";
    }

}
