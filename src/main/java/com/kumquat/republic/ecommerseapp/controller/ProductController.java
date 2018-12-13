package com.kumquat.republic.ecommerseapp.controller;

import com.kumquat.republic.ecommerseapp.models.Product;
import com.kumquat.republic.ecommerseapp.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    //    As a customer, I can filter search results by brand.
    @GetMapping("/get/{brand}")
    public List<Product> getResultsByBrand(@PathVariable String brand){
        return ProductRepository.getProductlist();
    }

    //    As a customer, I can filter search results by color.
    @GetMapping("/get/{brand}")
    public List<Product> getResultsByColor(@PathVariable String brand){
        return ProductRepository.getProductlist();
    }

    //    As a customer, I can filter search results by price range.
    @GetMapping("/get/{brand}")
    public List<Product> getResultsByPriceRange(@PathVariable String brand){
        return ProductRepository.getProductlist();
    }

    //    As a customer, I can combine multiple filters.
    @GetMapping("/get/{brand}")
    public List<Product> getResultsByBrandAndColor(@PathVariable String brand){
        return ProductRepository.getProductlist();
    }

    //    As a customer, I can order my search results by price, ascending.
    @GetMapping("/get/{brand}")
    public List<Product> getResultsByBSortedPriceAscending(@PathVariable String brand){
        return ProductRepository.getProductlist();
    }

    //    As a customer, I can order my search results by price, descending.
    @GetMapping("/get/{brand}")
    public List<Product> getResultsByBSortedPriceDescending(@PathVariable String brand){
        return ProductRepository.getProductlist();
    }
}
