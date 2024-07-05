package com.example.yeldpractice.dao;
import com.example.yeldpractice.pojo.Goods;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("SELECT g.gid, g.gname, g.gprice, g.gimg, " +
            "u.uid AS uid, u.uname AS uname, u.upass AS upass, u.utel AS utel, u.uemail AS uemail " +
            "FROM goods g " +
            "JOIN User u ON g.uid = u.uid")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "gprice", column = "gprice"),
            @Result(property = "gimg", column = "gimg"),
            @Result(property = "user.uid", column = "uid"),
            @Result(property = "user.uname", column = "uname"),
            @Result(property = "user.upass", column = "upass"),
            @Result(property = "user.utel", column = "utel"),
            @Result(property = "user.uemail", column = "uemail")
    })
    public List<Goods> show();
}
