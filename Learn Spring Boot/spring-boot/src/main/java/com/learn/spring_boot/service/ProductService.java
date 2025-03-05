package com.learn.spring_boot.service;

import com.learn.spring_boot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product> list = new ArrayList<>(
            Arrays.asList(
                    new Product(1,"Phone",20000),
                    new Product(2,"Laptop",60000),
                    new Product(3,"Tab",30000)
            )
    );

    public List<Product> getProducts() {
        return list;
    }

    public Product getProductById(int id) {
//        return list.stream()
//                .filter(product -> product.getId() == id)
//                .findFirst()
//                .orElse(null); // or throw an exception if needed
        for (Product product: list) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }


    public Product addProduct(Product product) {
        list.add(product);
        return product;
    }

    public Product updateProduct(Product updatedProduct) {
        for (Product product : list) {
            if (product.getId() == updatedProduct.getId()) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                return product;
            }
        }
        return null;
    }

    public String deleteProduct(int id) {
        for (Product product: list) {
            if (product.getId() == id) {
                list.remove(product);
                return "Product deleted successfully";
            }
        }
        return "Product not found";
    }
}
