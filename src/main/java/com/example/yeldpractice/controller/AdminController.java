package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.AdminDao;
import com.example.yeldpractice.pojo.Admin;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class AdminController {
    @Resource
    AdminDao adminDao;
    // 后台入口
    @GetMapping({"/Admin/adminLogin.html","/Admin/adminLogin"})
    public String adminEnter(){
        return "/Admin/adminLogin";
    }
    // 管理员登录请求
    @PostMapping("adminlogin")
    public String getAdmin(Admin admin, Model model, HttpSession session){
        try {
            admin = adminDao.showByAname(admin);
            if(admin!=null){
                // 如果没有抛出异常或者返回null说明查询到了
                session.setAttribute("adminAccessID",admin);
                // 身份通过 重定向到管理界面
                return "redirect:/Admin/adminMain.html";
            }
        }catch (MyBatisSystemException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        // 若抛出异常或者为空返回到登陆界面，并提示。（这里要是使用session存储重定向那么信息会一直存在。）
        model.addAttribute("msg","登陆失败！");
        return "/Admin/adminLogin";
    }
    // 古法拦截
//    @GetMapping("/Admin/adminMain")
//    public String interpret(HttpSession session, Model model) {
//        // 检查会话中是否存在管理员信息
//        Admin admin = (Admin) session.getAttribute("adminAccessID");
//        if (admin != null) {
//            // 如果有权限返回主视图。
//            return "/Admin/adminMain";
//        } else {
//            // 若没有权限，重定向到登录页面
//            return "redirect:/Admin/adminLogin.html";
//        }
//    }
    // 对于以下两个界面我们也需要做拦截。
//    @GetMapping("/Admin/postManagement")
//    public String inner1Interpret(HttpSession session){
//        Admin admin = (Admin) session.getAttribute("adminAccessID");
//        if (admin != null) {
//            // 如果有权限返回主视图。
//            return "/Admin/postManagement?page=1";
//        } else {
//            // 若没有权限，重定向到登录页面
//            return "redirect:/Admin/adminLogin.html";
//        }
//    }
//    @GetMapping("/Admin/userManagement.html")
//    public String inner2Interpret(HttpSession session){
//        Admin admin = (Admin) session.getAttribute("adminAccessID");
//        if (admin != null) {
//            // 如果有权限返回主视图。
//            return "/Admin/userManagement";
//        } else {
//            // 若没有权限，重定向到登录页面
//            return "redirect:/Admin/adminLogin.html";
//        }
//    }
}
