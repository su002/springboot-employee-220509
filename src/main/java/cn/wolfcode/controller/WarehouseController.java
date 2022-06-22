package cn.wolfcode.controller;


import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Warehouse;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.ProductService;
import cn.wolfcode.service.WarehouseService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseServiceImpl;
    @Autowired
    private ProductService productServiceImpl;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("queryObject") QueryObject queryObject){
        PageInfo pageInfo = warehouseServiceImpl.query(queryObject);

        model.addAttribute("pageInfo",pageInfo);
        return "warehouse/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Warehouse warehouse){
        warehouseServiceImpl.saveOrUpdate(warehouse);
        return "redirect:/warehouse/list";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        warehouseServiceImpl.deleteById(id);
        return "redirect:/warehouse/list";
    }
    @RequestMapping("/input")
    public String input(Long id,Model model){
        List<Product> products = productServiceImpl.selectList();
        model.addAttribute("products",products);
        if(id != null){
            Warehouse warehouse = warehouseServiceImpl.selectOne(id);
            model.addAttribute("warehouse",warehouse);
        }
        return "warehouse/input";
    }

}
