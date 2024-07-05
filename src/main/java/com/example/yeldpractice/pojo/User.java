package com.example.yeldpractice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int  uid;
    private String uname;
    private String upass;
    private String utel;
    private String uemail;
}
