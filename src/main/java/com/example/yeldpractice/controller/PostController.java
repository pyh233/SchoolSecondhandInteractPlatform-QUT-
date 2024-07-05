package com.example.yeldpractice.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    // 整个围绕postManagement跳转
    @GetMapping("/Admin/postManagement")
    public String showAllPost(Integer page, Model model){
        // 如果没有数字传来
        if(page==null||page==0){
            page=1;
        }
//        PageHelper.startPage(page,2);
//        PageInfo pi = new PageInfo(userDao.show());
//        model.addAttribute("pageInfo",pi);
        model.addAttribute("page",page);
        return "/Admin/postManagement";
    }
}
