package cn.wolfcode.controller;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Li hairui
 * @CreateTime: 2022/4/6 14:42
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionServiceImpl;

    @RequestMapping("/list")
    public String list(Model model, QueryObject qo) {
        PageInfo pageInfo = permissionServiceImpl.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "permission/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        permissionServiceImpl.delete(id);
        return "redirect:/permission/list";
    }

    @RequestMapping("/reload")
    //@RequiresPermissions(value = {"permission:reload"}, logical = Logical.OR)
    public String reload() {
        permissionServiceImpl.reload();
        return "redirect:/permission/list";
    }


}
