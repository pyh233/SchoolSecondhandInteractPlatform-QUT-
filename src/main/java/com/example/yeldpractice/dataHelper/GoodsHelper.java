package com.example.yeldpractice.dataHelper;

import com.example.yeldpractice.pojo.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsHelper {
    private String gname;
    private String gprofile;
    private double gprice;
    private String gimg;
    private String uname;
}
