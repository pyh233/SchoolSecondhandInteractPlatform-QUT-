package com.example.yeldpractice.dao;
import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("SELECT g.gid, g.gname, g.gprofile, g.gprice, g.gimg, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM goods g " +
            "JOIN User u ON g.uid = u.uid")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "gprofile", column = "gprofile"),
            @Result(property = "gprice", column = "gprice"),
            @Result(property = "gimg", column = "gimg"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public List<Goods> show();
    @Select("SELECT g.gid, g.gname, g.gprofile, g.gprice, g.gimg, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM goods g " +
            "JOIN User u ON g.uid = u.uid "+
            "WHERE g.gid=#{gid}")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "gprofile", column = "gprofile"),
            @Result(property = "gprice", column = "gprice"),
            @Result(property = "gimg", column = "gimg"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public Goods selectByGid(int gid);
    @Insert("insert into goods values(0,#{gname},#{gprofile},#{gprice},#{gimg},#{user.uid})")
    public int add(Goods goods);
    @Delete("delete from goods where gid=#{gid}")
    public int delete(int gid);
    @Select("select count(*) from goods")
    public int count();
    @Update("update goods set gname=#{gname}, gprofile=#{gprofile}, gprice=#{gprice}, gimg=#{gimg} where gid=#{gid}")
    public int set(Goods goods);

    // 关于其他表的方法
    @Select("select * from user where uname=#{uname}")
    public User selectUserByUname(String uname);
}
