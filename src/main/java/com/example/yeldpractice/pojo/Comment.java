package com.example.yeldpractice.pojo;

import javafx.geometry.Pos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int cid;
    private String cmcontent;
    private User user;
    private Post post;
}
