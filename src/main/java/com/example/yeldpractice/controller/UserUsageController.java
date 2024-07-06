package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.UserDao;
import com.example.yeldpractice.dao.UserUseDao;
import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.Post;
import com.example.yeldpractice.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserUsageController {
    @Resource
    UserUseDao userUseDao;
    // 用户浏览所有商品
    @GetMapping("userBrowserPosts")
    public @ResponseBody PageInfo userBrowserPosts(int page){
        PageHelper.startPage(page,2);
        PageInfo pageInfo = new PageInfo(userUseDao.UserBrowserPosts());
        return pageInfo;
    }
    // 用户浏览所有帖子
    @GetMapping("userBrowserGoods")
    public @ResponseBody PageInfo userBrowserGoods(int page){
        PageHelper.startPage(page,2);
        PageInfo pageInfo = new PageInfo(userUseDao.UserBrowserGoods());
        return pageInfo;
    }
    // TODO:用户浏览自己信息
    @PostMapping("BrowserSelf")
    public @ResponseBody User browserMySelf(int uid){
        return userUseDao.selectSelf(uid);
    }
    // 用户浏览自己的贴
    @PostMapping("browserSelfsPosts")
    public @ResponseBody List<Post> browserSelfsPosts(int uid){
        return userUseDao.selectSelfPosts(uid);
    }
    // 用户浏览自己的商品
    @PostMapping("browserSelfsGoods")
    public @ResponseBody List<Goods> browserSelfsGoods(int uid){
        return userUseDao.selectSelfGoods(uid);
    }
    // 用户发帖
    @PostMapping("postPost")
    public @ResponseBody int postPost(Post post){
        return userUseDao.postPost(post);
    }
    // 用户发布商品
    @PostMapping
    public @ResponseBody int postGood(Goods goods){
        return userUseDao.postGood(goods);
    }
}
