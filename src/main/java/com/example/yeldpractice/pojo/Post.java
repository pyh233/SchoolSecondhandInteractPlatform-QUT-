package com.example.yeldpractice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int pid;
    private String ptitle;
    private String profile;
    private String pcontent;
    private Goods goods;
}
