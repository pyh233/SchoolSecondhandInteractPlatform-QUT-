package com.example.yeldpractice.controller;

import com.example.yeldpractice.dao.GoodsDao;
import com.example.yeldpractice.dao.UserDao;
import com.example.yeldpractice.dataHelper.GoodsHelper;
import com.example.yeldpractice.dataHelper.GoodsTransfer;
import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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

    // 添加商品
    @GetMapping("/Admin/Goods/toAddGoodPage")
    public String toAddGoodPage(){
        return "/Admin/Goods/addGoodsPage";
    }
    // 其实这里可以用HttpServletRequest 取数据 没必要写一个辅助类。
    @PostMapping("/Admin/Goods/addGoods")
    public String addGoods(GoodsHelper goodsHelper,
                           @RequestParam(value = "file",required = true) MultipartFile multipartFile, HttpSession session){
        // 传进来的信息为 gname gprice gimg uname
        // 添加照片到本地服务器，并且把数据转为Goods数据，imgURL存储，使用临时存储路径 要修改为永久储存路径应该修改path file 并且修改数据库存储路径。
        String path = session.getServletContext().getRealPath("img");
        File file = new File(path,multipartFile.getOriginalFilename());
        if(!file.exists()){
            file.mkdirs();
        }
        try{
            multipartFile.transferTo(file);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        // 调用转化类把GoodsHelper转化为Goods
        goodsHelper.setGimg("/img/"+multipartFile.getOriginalFilename());
        GoodsTransfer goodsTransfer = new GoodsTransfer(goodsDao.selectUserByUname(goodsHelper.getUname()));
        Goods goods = goodsTransfer.transferToGoods(goodsHelper);
        System.out.println("Add goods:"+goods);
        try{
            int finalPage=1;
            goodsDao.add(goods);
            finalPage = goodsDao.count()/2 + goodsDao.count()%2;
            return "redirect:/Admin/goodManagement?page="+finalPage;
        }catch (Exception e){
            e.printStackTrace();
        }
        // 如果添加失败 添加错误信息 不添加page参数返回第一页。
        session.setAttribute("addGoodsFailure","Sorry!Add Goods message failure!");
        return "redirect:/Admin/goodManagement";
    }
    // 取消按钮重定向到商品管理界面
    @GetMapping({"/Admin/Goods/addCancel","/Admin/Goods/setCancel"})
    public String cancel(){
        return "redirect:/Admin/goodManagement";
    }
    // 删除
    @GetMapping("/Admin/Goods/deleteGoods")
    public String deleteGoods(int gid){
        goodsDao.delete(gid);
        return "redirect:/Admin/goodManagement";
    }
    // 修改
    @GetMapping("/Admin/Goods/setGoods")
    public String setGoods(int gid,Model model,HttpSession session){
        Goods modifyGood = goodsDao.selectByGid(gid);
        modifyGood.setGid(gid);
        // 传给页面
        model.addAttribute("modifyGood",modifyGood);
        // 传给请求方法(可以只用session，但是pyh233很懒)
        session.setAttribute("modifyGood",modifyGood);
        return "/Admin/Goods/modifyGoodsPage";
    }
    // 要一个一个参数写太麻烦 干脆用request.
    @PostMapping("/Admin/Goods/modify")
    public String modify(HttpServletRequest request,HttpSession session,
                         @RequestParam(value = "file",required = false)MultipartFile multipartFile){
        // 待修改的数据
        Goods modifyGood = (Goods) session.getAttribute("modifyGood");
        // 修改后的数据
        modifyGood.setGname(request.getParameter("gname"));
        modifyGood.setGprofile(request.getParameter("gprofile"));
        modifyGood.setGprice(Double.parseDouble(request.getParameter("gprice")));
        // 若图片也发生修改，先保存图片再修改数据库中存储数据 最后修改
        if(!multipartFile.getOriginalFilename().equals("")){
            String path = session.getServletContext().getRealPath("img");
            File file = new File(path,multipartFile.getOriginalFilename());

            if(!file.exists()){
                file.mkdirs();
            }

            try{
                multipartFile.transferTo(file);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
            // 将新的路径+文件名替换掉原来的名字
            modifyGood.setGimg("/img/"+multipartFile.getOriginalFilename());
        }
        System.out.println(modifyGood.getGimg());
        //
        goodsDao.set(modifyGood);
        return "redirect:/Admin/goodManagement";
    }

    @GetMapping("/test")
    public String show(Model model){
        model.addAttribute("pi",goodsDao.show());
        return "test";
    }
}
