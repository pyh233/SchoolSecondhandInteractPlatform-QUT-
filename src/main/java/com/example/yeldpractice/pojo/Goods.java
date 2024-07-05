package com.example.yeldpractice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private int gid;
    private String gname;
    private double gprice;
    private String gimg;
    private User user;
}
