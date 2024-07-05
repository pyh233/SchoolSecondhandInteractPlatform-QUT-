package com.example.yeldpractice.dao;

import com.example.yeldpractice.pojo.Admin;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminDao {
    @Select("select * from admin where aname=#{aname} and apass=#{apass}")
    public Admin showByAname(Admin admin);
}
