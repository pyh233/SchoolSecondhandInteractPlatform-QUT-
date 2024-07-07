package com.example.yeldpractice.dataHelper;

import com.example.yeldpractice.pojo.Post;
import com.example.yeldpractice.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentHelper {
    private int cid;
    private String cmcontent;
    private int uid;
    private int pid;
}
