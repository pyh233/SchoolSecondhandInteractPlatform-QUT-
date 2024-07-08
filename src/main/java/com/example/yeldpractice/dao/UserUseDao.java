package com.example.yeldpractice.dao;

import com.example.yeldpractice.dataHelper.CommentHelper;
import com.example.yeldpractice.pojo.Comment;
import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.Post;
import com.example.yeldpractice.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserUseDao {
    // 用户成功登录(查询到用户数据返回)
    @Select("select * from user where uname=#{uname} and upass=#{upass}")
    public User userLoginSelect(User user);
    // 用户注册检查用户名是否可用
    @Select("select count(*) from user where uname=#{uname}")
    public int registerCheck(String uname);
    // 用户注册成功添加用户信息。
    @Insert("insert into user values (0,#{uname},#{upass},#{utel},#{uemail})")
    public int add(User user);

    @Select("select * from post")
    public List<Post> UserBrowserPosts();
    @Select("select * from Goods")
    public List<Goods> UserBrowserGoods();
    // 用户查询个人主页
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
    // 用户发评论
    @Insert("insert into comments values(0,#{cmcontent},#{uid},#{pid})")
    public int giveCommemts(CommentHelper commentHelper);
    // 用户打开帖子后 帖子根据PID找到的Comments
    @Select("SELECT c.cmcontent, " +
            "u.uid AS uid, u.uname AS uname,  u.utel AS utel, u.uemail AS uemail, " +
            "p.pid AS pid, p.ptitle AS ptitle, p.profile AS profile, p.pcontent AS pcontent "+
            "FROM comments c " +
            "JOIN user u ON c.cmuid = u.uid "+
            "JOIN post p ON c.cmpid = p.pid "+
            "WHERE pid=#{pid}")
    @Results({
            @Result(property = "cmcontent", column = "cmcontent"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail"),
            @Result(property = "post.pid", column = "pid"),
            @Result(property = "post.ptitle", column = "ptitle"),
            @Result(property = "post.profile", column = "profile"),
            @Result(property = "post.pcontent", column = "pcontent")
    })
    public List<Comment> getCommentsByPid(int pid);
    // 用户修改自己的某一个贴
    @Update("update post set ptitle=#{ptitle},profile=#{profile},pcontent=#{pcontent} where pid=#{pid} and uid=#{user.uid}")
    public int setMyPost(Post post);
    // 用户修改自己的某一个good(涉及上传图片)
    @Update("update post set gname=#{gname},gprofile=#{gprofile},gprice=#{gprice} where gimg=#{gimg} and uid=#{user.uid}")
    public int setMyGood(Goods goods);
    // 用户删帖
    @Delete("delete from post where pid=#{pid}")
    public int deleteMyPost(int pid);
    // 用户删物品
    @Delete("delete from post where gid=#{gid}")
    public int deleteMyGood(int gid);
}
