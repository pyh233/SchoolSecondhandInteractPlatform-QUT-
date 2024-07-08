package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.UserDao;
import com.example.yeldpractice.dao.UserUseDao;
import com.example.yeldpractice.dataHelper.CommentHelper;
import com.example.yeldpractice.pojo.Comment;
import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.Post;
import com.example.yeldpractice.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserUsageController {
    @Resource
    UserUseDao userUseDao;

    // 用户注册的时候检查用户名是否存在
    @PostMapping("registerCheck")
    public @ResponseBody int registerCheck(String uname){
        // 返回1为存在用户名 返回0可以继续注册
        return userUseDao.registerCheck(uname);
    }
    // 用户注册（请求添加用户添加的注册数据有uname,upass,utel,uemail）
    @PostMapping("userRegister")
    public @ResponseBody int userRegister(@RequestBody User user){
        return userUseDao.add(user);
    }
    // 用户登入（登入成功后）
    @PostMapping("userLoginIn")
    // 登陆接口 返回null登陆失败（需要注册或者重新输入用户名或密码）
    public @ResponseBody User userLoginIn(@RequestBody User user){
        return userUseDao.userLoginSelect(user);
    }


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
    // 用户浏览自己信息
    @PostMapping("BrowserSelf")
    public @ResponseBody User browserMySelf(int uid){
        return userUseDao.selectSelf(uid);
    }
    // 用户浏览自己所有的贴
    @PostMapping("browserSelfsPosts")
    public @ResponseBody List<Post> browserSelfsPosts(int uid){
        return userUseDao.selectSelfPosts(uid);
    }
    // 用户浏览自己所有的商品
    @PostMapping("browserSelfsGoods")
    public @ResponseBody List<Goods> browserSelfsGoods(int uid){
        return userUseDao.selectSelfGoods(uid);
    }
    // 用户发帖
    @PostMapping("postPost")
    public @ResponseBody int postPost(@RequestBody Post post){
        return userUseDao.postPost(post);
    }
    // 用户发布商品
    @PostMapping("postGood")
    public @ResponseBody int postGood(@RequestBody Goods goods){
        return userUseDao.postGood(goods);
    }
    // 用户修改自己发布的帖子
    @PostMapping("setMyPost")
    public @ResponseBody int setMyPost(@RequestBody Post post){
        return userUseDao.setMyPost(post);
    }

    // 用户修改自己发布的商品信息
    // TODO:未完成涉及图片上传，待完善。
    @PostMapping("setMyGood")
    public @ResponseBody int setMyGood(@RequestBody Goods goods){
        return userUseDao.setMyGood(goods);
    }

    // 用户删除自己的帖
    @PostMapping("deleteMyPost")
    public @ResponseBody int deleteMyPost(int pid){
        return userUseDao.deleteMyPost(pid);
    }

    // 用户删除自己的商品
    @PostMapping("deleteMyGood")
    public @ResponseBody int deleteMyGood(int gid){
        return userUseDao.deleteMyGood(gid);
    }

    // 用户在帖子下留言
    @PostMapping("postComment")
    public @ResponseBody int postComment(@RequestBody CommentHelper commentHelper){
        return userUseDao.giveCommemts(commentHelper);
    }
    // 用户浏览他人帖子（需要留言返回帖子）
    @PostMapping("commentsShowOnPost")
    public @ResponseBody List<Comment> commentsShowOnPost(int pid){
        return userUseDao.getCommentsByPid(pid);
    }
    // TODO:查询特定商品
    // TODO:查询特定
}
