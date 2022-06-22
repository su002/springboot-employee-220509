package cn.wolfcode.controller;


import cn.wolfcode.domain.Course;
import cn.wolfcode.query.JsonResoult;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.CourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

        @RequestMapping("/list")
        public String list(Model model, QueryObject qo ) {
            PageInfo pageInfo = courseService.query(qo);

            model.addAttribute("pageInfo", pageInfo);

            return "course/list";
    }
    @RequestMapping("/test")
    public JsonResoult test() {

        return JsonResoult.success("rtue",true);
    }


    @RequestMapping("/input")
    public String input(Long id, Model model) {
        List<Course> result = courseService.selectAll();
        model.addAttribute("permissions",result);
        if (id != null) {
            //数据回显
            Course role = courseService.selectOne(id);
//            List<Permission> permissions = permissionServiceImpl.selectPermissionByRoleId(role.getId());
//            model.addAttribute("ownPermissions",permissions);
            model.addAttribute("roles", role);
        }
        return "course/input";
    }

    /**
     * id:
     * name: cto
     * sn: cto003
     * permissionIds: 1
     * permissionIds: 2
     * permissionIds: 3
     */
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Course course) {
        courseService.saveOrUpdate(course);
        return "redirect:/course/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        courseService.delete(id);
        return "redirect:/course/list";
    }

    @RequestMapping("/subject")
    public String subject(Long id , Model model ,QueryObject queryObject) {
        PageInfo studentList = courseService.selectCourseAndStudents(id ,queryObject );
        model.addAttribute("pageInfo" , studentList);
        return "course/subject";
    }










    @RequestMapping("/ss2")
    public String saveOrUpdate01(){
        return "";
    }

//    @RequestMapping("/ss")
//    public String input01(Long id,Model model , Long[] permissionIds){
//        List<Role> roles = roleServiceImpl.selectAll();
//        model.addAttribute("roles",roles);
//
//        if (id != null) {
//            Role role = roleServiceImpl.selectOne(id);
//            model.addAttribute("role",role);
//            List<Permission> permissionList =   roleServiceImpl.selectRoleAndPerById(role.getId());
//            model.addAttribute("per" , permissionList);
//        }
//
//        return "redirect:role/input";
//    }
}
