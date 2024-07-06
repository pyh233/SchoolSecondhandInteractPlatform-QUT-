package com.example.yeldpractice.dao;

import com.example.yeldpractice.pojo.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostDao {
    @Select("SELECT p.pid, p.ptitle, p.profile, p.pcontent, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM post p " +
            "JOIN User u ON p.puid = u.uid")
    @Results({
            @Result(property = "pid", column = "pid"),
            @Result(property = "ptitle", column = "ptitle"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "pcontent", column = "pcontent"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public List<Post> showAllPosts();
    @Delete("delete from post where pid=#{pid}")
    public int delete(int pid);
}
