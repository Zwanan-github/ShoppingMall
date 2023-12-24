package com.zwanan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
    private Integer role;
}
