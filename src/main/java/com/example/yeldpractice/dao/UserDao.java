package com.example.yeldpractice.dao;

import com.example.yeldpractice.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user")
    public List<User> show();
    @Select("select * from user where uid=#{uid}")
    public User selectByUID(int uid);
    @Select("select * from user where uname=#{uname}")
    public User selectByUname(String uname);
    @Insert("insert into user values (0,#{uname},#{upass},#{utel},#{uemail})")
    public int add(User user);
    // 血与泪的教训 忘写条件 全改了T^T
    @Update("update user set uname=#{uname},upass=#{upass},utel=#{utel},uemail=#{uemail} where uid=#{uid}")
    public int set(User user);
    @Delete("delete from user where uid=#{uid}")
    public int remove(int uid);
    @Select("select count(*) from user")
    public int count();
    // 用户成功登录(查询到用户数据返回)
    @Select("select * from user where uname=#{uname} and upass=#{upass}")
    public User userLoginSelect(User user);
    // 用户注册检查用户名是否可用
    @Select("select count(*) from user where uname=#{uname}")
    public int registerCheck(String uname);
}
