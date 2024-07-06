package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.UserDao;
import com.example.yeldpractice.pojo.Post;
import com.example.yeldpractice.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Resource
    UserDao userDao;
    // 查询用户界面请求 管理员管理用户界面整个围绕userManagement跳转
    @GetMapping("/Admin/userManagement")
    public String showAllUser(Integer page, Model model){
        // 如果没有数字传来
        if(page==null||page==0){
            page=1;
        }
        PageHelper.startPage(page,2);
        PageInfo pi = new PageInfo(userDao.show());
        model.addAttribute("pageInfo",pi);
        return "/Admin/userManagement";
    }
    // 添加用户并跳转查询
    @GetMapping("/Admin/toAddUserPage")
    public String toAddUserPage(){
        return "/Admin/addUserPage";
    }
    @PostMapping("/Admin/addOneUser")
    public String addOneUser(User user){
        userDao.add(user);
        return "redirect:/Admin/userManagement";
    }
    // 删除用户并跳转查询
    @GetMapping("/Admin/deleteOneUser")
    public String deleteOneUser(int uid){
        int result = userDao.remove(uid);
        return "redirect:/Admin/userManagement";
    }
    // 修改用户跳转查询
    @GetMapping("/Admin/setOneUser")
    public String setOneUser(int uid, Model model, HttpSession session){
        model.addAttribute("modifyUser",userDao.selectByUID(uid));
        session.setAttribute("modifyUserUID",uid);
        return "/Admin/modifyPage";
    }
    @PostMapping("/Admin/modifyUser")
    public String modifyUser(User user,HttpSession session){
        System.out.println(user);
        int modifyUserUID = Integer.parseInt(session.getAttribute("modifyUserUID").toString());
        user.setUid(modifyUserUID);
        userDao.set(user);
        return "redirect:/Admin/userManagement";
    }
    // 取消修改取消添加 跳转
    @GetMapping({"/Admin/modifyCancel","/Admin/addCancel"})
    public String cancel(){
        return "redirect:/Admin/userManagement";
    }


    // 用户注册的时候检查用户名是否存在
    @PostMapping("registerCheck")
    public @ResponseBody int registerCheck(String uname){
        // 返回1为存在用户名 返回0可以继续注册
        return userDao.registerCheck(uname);
    }
    // 用户注册（请求添加用户添加的注册数据有uname,upass,utel,uemail）
    @PostMapping("userRegister")
    public @ResponseBody int userRegister(User user){
        return userDao.add(user);
    }
    // 用户登入（登入成功后）
    @PostMapping("userLoginIn")
    // 登陆接口 返回null登陆失败（需要注册或者重新输入用户名或密码）
    public @ResponseBody User userLoginIn(User user){
        return userDao.userLoginSelect(user);
    }
}
