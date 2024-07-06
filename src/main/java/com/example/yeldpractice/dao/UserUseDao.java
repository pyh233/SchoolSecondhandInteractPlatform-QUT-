package com.example.yeldpractice.dao;

import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.Post;
import com.example.yeldpractice.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserUseDao {
    @Select("select * from post")
    public List<Post> UserBrowserPosts();
    @Select("select * from Goods")
    public List<Goods> UserBrowserGoods();
    // 用户查询主页
    @Select("select * from user where uid=#{uid}")
    public User selectSelf(int uid);
    // 用户查询自己发de帖
    @Select("select * from post where puid=#{uid}")
    public List<Post> selectSelfPosts(int uid);
    // 用户查询自己发布的商品
    @Select("select * from goods where uid=#{uid}")
    public List<Goods> selectSelfGoods(int uid);
    // 用户发帖
    @Insert("insert into post values (0,#{ptitle},#{profile},#{pcontent},#{user.uid})")
    public int postPost(Post post);
    // 用户发布商品
    @Insert("insert into goods values(0,#{gname},#{gprofile},#{gprice},#{gimg},#{user.uid})")
    public int postGood(Goods goods);

}
