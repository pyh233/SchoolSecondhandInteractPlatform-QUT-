package com.example.yeldpractice.dataHelper;

import com.example.yeldpractice.pojo.Goods;
import com.example.yeldpractice.pojo.User;

public class GoodsTransfer {
    private Goods goods;
    private User user;
    public GoodsTransfer(User user){
        this.user = user;
        this.goods = new Goods();
    }
    public Goods transferToGoods(GoodsHelper goodsHelper){
        this.goods.setGname(goodsHelper.getGname());
        this.goods.setGprofile(goodsHelper.getGprofile());
        this.goods.setGprice(goodsHelper.getGprice());
        this.goods.setGimg(goodsHelper.getGimg());
        this.goods.setUser(this.user);
        return this.goods;
    }
}
