package cn.wolfcode.controller;

import cn.wolfcode.domain.Department;
import cn.wolfcode.noon.RequirePremission;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentServiceImpl;

    @RequestMapping("/list")
    public ModelAndView list(Model model, QueryObject queryObject){
        ModelAndView mv = new ModelAndView();
        PageInfo pageInfo = departmentServiceImpl.query(queryObject);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("department/list");
        return mv;
    }


    @RequirePremission(name = "修改员工" , expression ="dept:update" )
    @RequestMapping("/input")
    public String input(Long id, Model model){
        if (id != null){
          Department department = departmentServiceImpl.selectOne(id);
            model.addAttribute("department",department);
        }
        return "department/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department){
        departmentServiceImpl.saveOrUpdate(department);
        return "redirect:/department/list";
    }

    @RequirePremission(name = "删除员工" , expression ="dept:update" )
    @RequestMapping("/delete")
    public String deleteDepartment(Long id){
        departmentServiceImpl.deleteById(id);
        return "redirect:/department/list";
    }
}
