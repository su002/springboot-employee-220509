package cn.wolfcode.controller;

import cn.wolfcode.domain.Mall;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.MallService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mall")
public class MallController {
    @Autowired
    private MallService mallServiceImpl;
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("queryObject") QueryObject queryObject){
        PageInfo pageInfo = mallServiceImpl.query(queryObject);
        model.addAttribute("pageInfo",pageInfo);
        return "mall/list";
    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Mall mall){
        mallServiceImpl.saveOrUpdate(mall);
        return "redirect:/mall/list";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        mallServiceImpl.deleteById(id);
        return "redirect:/mall/list";
    }
    @RequestMapping("/input")
    public String input(Long id,Model model){
        if(id != null){
            Mall mall = mallServiceImpl.selectOne(id);
            model.addAttribute("mall",mall);
        }
        return "mall/input";
    }

}
