package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.PostDao;
import com.example.yeldpractice.pojo.Post;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class PostController {
    // 整个围绕postManagement跳转
    @Resource
    PostDao postDao;

    @GetMapping("/Admin/postManagement")
    public String showAllPosts(Integer page, Model model) {
        if (page == null || page == 0) {
            page = 1;
        }
        PageHelper.startPage(page, 6);
        PageInfo pi = new PageInfo(postDao.showAllPosts());
        model.addAttribute("pageInfo", pi);
        return "/Admin/postManagement";
    }
    @GetMapping("/Admin/Post/deletePost")
    public String removePost(int pid){
        postDao.delete(pid);
        return "redirect:/Admin/postManagement";
    }
}
