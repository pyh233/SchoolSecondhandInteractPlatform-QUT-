package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.GoodsDao;
import com.example.yeldpractice.pojo.Goods;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class GoodsController {
    @Resource
    GoodsDao goodsDao;
    @GetMapping("/Admin/goodManagement")
    public String showAllGoods(Integer page, Model model){
        // 如果没有数字传来
        if(page==null||page==0){
            page=1;
        }
        PageHelper.startPage(page,2);
        PageInfo pi = new PageInfo(goodsDao.show());
        model.addAttribute("pageInfo",pi);
        return "/Admin/goodManagement";
    }
    @GetMapping("/test")
    public String show(Model model){
        model.addAttribute("pi",goodsDao.show());
        return "test";
    }
}
