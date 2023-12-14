package com.zwanan.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String gender;
    private String birthday;
    private String phone;
    private String address;
    private String personal;
}
