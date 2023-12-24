package com.zwanan.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cart {
    private Integer id;
    private Product product;
    private String submitTime;
}
