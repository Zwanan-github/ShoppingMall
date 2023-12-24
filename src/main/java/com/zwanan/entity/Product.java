package com.zwanan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private String pic;
    private String category;
    private BigDecimal price;
    private Integer number;
    private String memo;
}
