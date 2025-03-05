package com.learn.spring_boot.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Product {
    private int id;
    private String name;
    private double price;
}
