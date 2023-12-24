package com.zwanan.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Product product;
    private String submitTime;
}
