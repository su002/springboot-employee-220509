package cn.wolfcode.controller;


import cn.wolfcode.domain.Product;
import cn.wolfcode.domain.Shop;
import cn.wolfcode.query.ProductQueryObject;
import cn.wolfcode.service.ProductService;
import cn.wolfcode.service.ShopService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productServiceImpl;
    @Autowired
    private ShopService shopServiceImpl;

    @RequestMapping("/list")
    public String list(@ModelAttribute("queryObject") ProductQueryObject queryObject, Model model) {
        PageInfo pageInfo = productServiceImpl.query(queryObject);
        List<Shop> shops = shopServiceImpl.selectAll();
        model.addAttribute("shops",shops);
        model.addAttribute("pageInfo", pageInfo);
        return "product/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Product product) {
        productServiceImpl.saveOrUpdate(product);
        return "redirect:/product/list";
    }

    @RequestMapping("/delete")
    public String deleteById(Long id) {
        productServiceImpl.deleteById(id);
        return "redirect:/product/list";
    }

    @RequestMapping("/input")
    public String input(Long id, Model model) {
        List<Shop> shops = shopServiceImpl.selectAll();
        model.addAttribute("shops",shops);
        if (id != null) {
           Product product = productServiceImpl.selectOne(id);
            model.addAttribute("product", product);
        }
        return "product/input";
    }

}
