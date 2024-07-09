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
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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


    // 用户浏览所有Post(√)
    @PostMapping("userBrowserPosts")
    public @ResponseBody PageInfo userBrowserPosts(@RequestParam int page){
        PageHelper.startPage(page,2);
        PageInfo pageInfo = new PageInfo(userUseDao.UserBrowserPosts());
        return pageInfo;
    }
    // 用户浏览所有Good(√)
    @PostMapping("userBrowserGoods")
    public @ResponseBody PageInfo userBrowserGoods(@RequestParam int page){
        PageHelper.startPage(page,4);
        PageInfo pageInfo = new PageInfo(userUseDao.UserBrowserGoods());
        return pageInfo;
    }
    // 用户浏览自己信息(√)貌似没用到==没什么d用
    @PostMapping("BrowserSelf")
    public @ResponseBody User browserMySelf(int uid){
        return userUseDao.selectSelf(uid);
    }

    // 用户浏览自己所有的贴(√)
    @PostMapping("browserSelfsPosts")
    public @ResponseBody List<Post> browserSelfsPosts(@RequestBody Map<String,Integer> pyload){
        int uid = (int)pyload.get("uid");
        return userUseDao.selectSelfPosts(uid);
    }
    // 用户浏览自己所有的商品(√)
    @PostMapping("browserSelfsGoods")
    public @ResponseBody List<Goods> browserSelfsGoods(@RequestBody Map<String,Integer> pyload){
        int uid = (int)pyload.get("uid");
        return userUseDao.selectSelfGoods(uid);
    }


    // 用户发帖(√)这个方法需要进行限制,防止用户插入过多数据
    @PostMapping("postPost")
    public @ResponseBody int postPost(@RequestBody Post post){
        return userUseDao.postPost(post);
    }
    // 用户发布商品(图片上传，前面以base64编码发来数据，需要在后台解码保存图片)
    // 设置默认值拖鞋.jpg
    @PostMapping("postGood")
    public @ResponseBody int postGood(@RequestBody Goods goods,HttpSession session) {
        // 处理图片上传,得到存储到数据库中的数据
        String imagePath = saveImage(goods.getGimg(),session);
        System.out.println(imagePath);
        // 将图片路径设置到 goods 对象中，保存数据
        if(goods.getGimg().equals("")){
            imagePath="/img/OIP-C.jpg";
        }
        goods.setGimg(imagePath);
        return userUseDao.postGood(goods);
    }

    // 保存图片到服务器
    private String saveImage(String base64Image, HttpSession session) {
        String imagePath = ""; // 初始化图片路径

        try {
            // 解码 base64 编码的图片数据并保存到服务器
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            String imageName = UUID.randomUUID().toString() + ".jpg"; // 生成唯一的图片名


            // 下面是保存文件
            // !!!!!!!!!!!!!!!!!!!!!!需要保存到两个位置很重要因为只有项目重构后temp里面才会存储东西
            // 先保存临时文件
            // 1.创建临时目录
            String tempPath = session.getServletContext().getRealPath("img");
            if(!new File(tempPath).exists()){
                new File(tempPath).mkdirs();
            }
            // 2.上传临时文件
            String tempImgFileURL = tempPath + File.separator + imageName;
            File tmpImg = new File(tempImgFileURL);
            FileUtils.writeByteArrayToFile(tmpImg,imageBytes);
            // 再上传永久文件
            // 1.创建永久目录
            String path = "G:\\Java\\JavaWeb\\JWfileProjects\\yeldPractice\\src\\main\\resources\\templates\\img";
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 上传文件
            // 2.上传永久文件
            String filePath = path + File.separator + imageName;
            File imageFile = new File(filePath);
            // 写入图片文件
            FileUtils.writeByteArrayToFile(imageFile, imageBytes);


            // 设置图片路径
            imagePath = "/img/" + imageName; // 假设图片保存在 /img/ 目录下
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagePath;
    }
    // 用户修改自己发布的帖子(√)
    @PostMapping("selectPostByPid")
    public @ResponseBody Post selectPostByPid(int pid){
        return userUseDao.selectByPid(pid);
    }
    @PostMapping("setMyPost")
    public @ResponseBody int setMyPost(@RequestBody Post post){
        return userUseDao.setMyPost(post);
    }

    // 用户修改自己发布的商品信息(?)
    @PostMapping("selectGoodByGid")
    public @ResponseBody Goods selectGoodByGid(int gid){
        return userUseDao.selectGoodByGid(gid);
    }
    @PostMapping("setMyGood")
    public @ResponseBody int setMyGood(@RequestBody Goods goods,HttpSession session){
        System.out.println(goods);
        // 处理图片上传,得到存储到数据库中的数据
        String imagePath = saveImage(goods.getGimg(),session);

        if (goods.getGimg() != null && !goods.getGimg().isEmpty() && !goods.getGimg().startsWith("/img/")) {
            imagePath = saveImage(goods.getGimg(), session);
        }
        // 将图片路径设置到 goods 对象中，保存数据
        if(goods.getGimg().equals("")){
            imagePath="/img/OIP-C.jpg";
        }
        goods.setGimg(imagePath);
        return userUseDao.setMyGood(goods);
    }

    // 用户删除自己的帖(√)
    @PostMapping("deleteMyPost")
    public @ResponseBody int deleteMyPost(int pid){
        return userUseDao.deleteMyPost(pid);
    }

    // 用户删除自己的商品(√)
    @PostMapping("deleteMyGood")
    public @ResponseBody int deleteMyGood(int gid){
        return userUseDao.deleteMyGood(gid);
    }

    // 用户在帖子下留言(√)
    @PostMapping("postComment")
    public @ResponseBody int postComment(@RequestBody CommentHelper commentHelper){
        return userUseDao.giveCommemts(commentHelper);
    }
    // 用户浏览他人帖子（需要留言返回帖子）(√)
    @PostMapping("commentsShowOnPost")
    public @ResponseBody List<Comment> commentsShowOnPost(int pid){
        return userUseDao.getCommentsByPid(pid);
    }
    // TODO:查询特定商品
    // TODO:查询特定
}
