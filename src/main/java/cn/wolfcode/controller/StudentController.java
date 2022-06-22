package cn.wolfcode.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.Student;
import cn.wolfcode.query.EmployeeQueryObject;
import cn.wolfcode.service.DepartmentService;
import cn.wolfcode.service.EmployeeService;
import cn.wolfcode.service.RoleService;
import cn.wolfcode.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;



    @RequestMapping("/list")
    public String list(@ModelAttribute("queryObject") EmployeeQueryObject queryObject, Model model) {
        PageInfo pageInfo = studentService.query(queryObject);
        List<Student> departments = studentService.selectAll();
        model.addAttribute("departments",departments);
        model.addAttribute("pageInfo", pageInfo);
        return "student/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Student student,Long[] permissionIds) {
        studentService.saveOrUpdate(student,permissionIds);
        return "redirect:/student/list";
    }


    @RequestMapping("/delete")
    public String deleteDepartment(Long id) {
        studentService.delete(id);
        return "redirect:/student/list";
    }

//    @RequestMapping("/batchDelete")
//    public String deleteDepartment(List<Long> empId) {
//        employeeServiceImpl.batchDeleteById(empId);
//        return "redirect:/student/list";
//    }


    @RequestMapping("/input")
    public String input(Long id, Model model) {
        List<Student> department = studentService.selectAll();
        List<Student> roles = studentService.selectAll();
        List<Student> ownRoles = studentService.selectRoleByEmpId(id);

        model.addAttribute("ownRoles",ownRoles);
        model.addAttribute("roles",roles);
        model.addAttribute("students",department);
        if (id != null) {
            Student employee = studentService.selectOne(id);
            model.addAttribute("employee", employee);
        }
        return "student/input";
    }

}
