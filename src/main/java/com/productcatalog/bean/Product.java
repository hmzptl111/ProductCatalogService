package com.productcatalog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private long id;
    private String code;
    private String name;
    private String description;
    private double price;
}