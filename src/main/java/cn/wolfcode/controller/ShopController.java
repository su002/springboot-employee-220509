package cn.wolfcode.controller;


import cn.wolfcode.domain.*;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.EmployeeService;
import cn.wolfcode.service.MallService;
import cn.wolfcode.service.ShopService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopServiceImpl;
    @Autowired
    private MallService mallServiceImpl;
    @Autowired
    private EmployeeService employeeMapperImpl;

    @RequestMapping("/list")
    public String list(@ModelAttribute("queryObject") QueryObject queryObject, Model model) {
        PageInfo pageInfo = shopServiceImpl.query(queryObject);
        model.addAttribute("pageInfo", pageInfo);
        return "shop/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Shop shop) {
        shopServiceImpl.saveOrUpdate(shop);
        return "redirect:/shop/list";
    }

    @RequestMapping("/delete")
    public String deleteById(Long id) {
        shopServiceImpl.deleteById(id);
        return "redirect:/shop/list";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {
        List<Mall> malls = mallServiceImpl.selectAll();
        List<Employee> employees = employeeMapperImpl.selectAll();
        model.addAttribute("malls",malls);
        model.addAttribute("employees",employees);
        if (id != null) {
           Shop shop = shopServiceImpl.selectOne(id);
            model.addAttribute("shop", shop);
        }
        return "shop/input";
    }

}
