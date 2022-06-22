package cn.wolfcode.controller;


import cn.wolfcode.domain.Employee;
import cn.wolfcode.query.JsonResoult;
import cn.wolfcode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 1、根据账号和密码查询用户是否存在。
 * 存在  设置到session作用域，目的是 整个项目要用。主要是在拦截器 需要知道当前用户是否登录
 * <p>
 * 2、跳转到列表页面
 * <p>
 * <p>
 * 在访问控制器之前都会先访问 拦截器
 * 所以在拦截器 需要知道当前用户是否登录。从session中获取
 * <p>
 * 没有登录成功就跳转到登录页面重新输账号和密码
 */

@Controller
public class LoginController {
    private static final String COUNT = "count";
    private static final Integer MIN_COUNT = 0;

    private static final Integer MAX_COUNT = 3;
    @Autowired
    HttpServletRequest req;
    @Autowired
    ServletContext request;
    @Autowired
    private UserService userServiceImpl;

    @Autowired
    HttpSession session;


    @RequestMapping("/login")
    @ResponseBody
    public JsonResoult login04(@RequestParam("username")String username,
                               @RequestParam("password")String password
                              ) {



        Employee user = userServiceImpl.selectUser(username, password);

        Employee nameandcount = userServiceImpl.selectCountAndName(username);
        //密码错误次数
        int count = nameandcount.getCount();
//        状态
        int status = nameandcount.getStatus();

        if (user != null && status !=0) {
            req.getSession().setAttribute("user",user);
            String id = session.getId();
            System.out.println(id);
            return JsonResoult.success("成功",user);
        }else
        if (count <= 2 && status == 1) {
            userServiceImpl.updateCount(username);
            return JsonResoult.fail("失败"+count+"次" ,false );

        }else {
            userServiceImpl.updateStatus(username);
            return JsonResoult.fail("失败"+count+"次 ,账号已被锁定" ,false );
        }
    }







    @RequestMapping("/login2")
    @ResponseBody
    public JsonResoult login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpSession session,
                        Model model) {

        Integer count = (Integer)session.getAttribute(COUNT);

        Employee user = userServiceImpl.selectUser(username, password);

        if (user == null){
            if (count ==null){
                count =1;
                session.setAttribute("count",count);
            }else {
                count++;
                session.setAttribute("count" ,count);
            }if (count>3) {
                if (session.getAttribute("user").equals(user.getUsername())) {
                    return JsonResoult.fail("今日次数已用完,账户已锁定",false);
                }
                return JsonResoult.fail("今日次数已用完,账户已锁定",false);
            }
            return JsonResoult.fail("失败"+count+"次",false);
        }
        if (password.equals(user.getPassword()) && username.equals(user.getUsername())){

            session.setAttribute("user",user);
        }
        return JsonResoult.success("成功",true);
    }




    @RequestMapping("/login054")
    @ResponseBody
    public JsonResoult login02(@RequestParam("username")String username,
                               @RequestParam("password")String password,
                               HttpSession session) {

        req.getSession().setAttribute("count" ,COUNT);
        Employee user = userServiceImpl.selectUser(username, password);
        Integer count2 =(Integer) request.getAttribute("count");

        if (user != null) {
            req.getSession().setAttribute("user",user);
            return JsonResoult.success("成功",user);
        }
        Integer count01  =(Integer) req.getAttribute("count");
        Integer count = (Integer) request.getAttribute(COUNT);
        if (count == null && user == null) {
            count = 1;

            req.setAttribute("count", count);
            Integer count1 =(Integer) req.getAttribute("count");

            request.setAttribute("count" ,count);
            return JsonResoult.fail("失败"+count+"次" ,false );
        }
        count++;
        request.setAttribute("count" ,count);
        if(count <= MAX_COUNT) {
            return JsonResoult.fail("失败"+count+"次" ,false );
        }
//        否则失败
        return JsonResoult.fail("账号被锁定",false);
    }







    @RequestMapping("/login1")
    @ResponseBody
    public JsonResoult login03(@RequestParam("username")String username,
                               @RequestParam("password")String password,
                               HttpSession session) {

//        req.getSession().setAttribute("mincount" ,MIN_COUNT);
        req.setAttribute("mincount",MIN_COUNT);
        Employee user = userServiceImpl.selectUser(username, password);
        Integer count2 =(Integer) request.getAttribute("mincount");

        if (user != null && count2 <= MAX_COUNT) {
            req.getSession().setAttribute("user",user);
            return JsonResoult.success("成功",user);
        }
        Integer count01  =(Integer) req.getAttribute("mincount");
        Integer count = (Integer) request.getAttribute(String.valueOf(MIN_COUNT));
        if (count == null && user == null) {
            count = 1;

            req.setAttribute("mincount", MIN_COUNT);
            Integer count1 =(Integer) req.getAttribute("mincount");

            request.setAttribute("mincount" ,MIN_COUNT);
            return JsonResoult.fail("失败"+MIN_COUNT+"次" ,false );
        }
        count++;
        request.setAttribute("mincount" ,MIN_COUNT);
        if(count <= MAX_COUNT) {
            return JsonResoult.fail("失败"+MIN_COUNT+"次" ,false );
        }
//        否则失败
        return JsonResoult.fail("账号被锁定",false);
    }


    @RequestMapping("/logout")
    public JsonResoult loginout(HttpServletResponse resp) throws IOException {
        req.removeAttribute("user");

        resp.sendRedirect("/static/login.html");
        return JsonResoult.success("退出成功");

    }







    @ResponseBody
    @RequestMapping("/test")
    public JsonResoult test(HttpSession session) {
        Integer count = (Integer)session.getAttribute(COUNT);
        if (count == null) {
            count = 1;
            session.setAttribute("count" ,count);
        }else {
            count++;
            session.setAttribute("count" ,count);
        }
        return JsonResoult.success("成功"  ,count);
    }



//    @ResponseBody
//    @RequestMapping("/mess")
//    public JsonResoult test() {
//        ArrayList<Object> objects = new ArrayList<>();
//        ArrayList<Object> objects1 = new ArrayList<>();
//        price messList = new price();
//
//        domian.TestJson testJson = new domian.TestJson();
//
//        testJson.setId(1);
//        testJson.setName("电工群");
//        Date date = new Date();
//        long time = date.getTime();
//        testJson.setDate(time);
//        testJson.setDisable(true);
//        testJson.setUnrnum(20);
//        objects.add(testJson);
//
//        messList.setPrice("图片");
//        objects1.add(messList);
//        testJson.setPlist(objects1);
//
//        Message message = new Message();
//        message.setId(1);
//        message.setContext("找到工作");
//        message.setType("text");
//
//        ArrayList<Object> objects2 = new ArrayList<>();
//        objects2.add(message);
//        testJson.setMessager(objects2);
//
//        return  JsonResoult.success("成功",objects);
//    }

}
