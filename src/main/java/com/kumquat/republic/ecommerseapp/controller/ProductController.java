package com.kumquat.republic.ecommerseapp.controller;

import com.kumquat.republic.ecommerseapp.models.Product;
import com.kumquat.republic.ecommerseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import static java.util.Comparator.comparing;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    //    As a customer, I can filter search results by brand.
    @GetMapping("/get/byBrand/{brand}")
    public List<Product> getResultsByBrand(@PathVariable String brand){
        return productRepository.getProductlist().stream().parallel().filter(p -> p.getBrand().equals(brand)).collect(toList());
    }

    //    As a customer, I can filter search results by color.
    @GetMapping("/get/byColor/{color}")
    public List<Product> getResultsByColor(@PathVariable String color){
        return productRepository.getProductlist().stream().parallel().filter(p -> p.getColor().equals(color)).collect(toList());
    }

    //    As a customer, I can filter search results by price range.
    @GetMapping("/get/price/{fromPrice}/{toPrice}")
    public List<Product> getResultsByPriceRange(@PathVariable double fromPrice, @PathVariable double toPrice){
        return productRepository.getProductlist().stream().parallel().filter(p -> (p.getPrice()>=fromPrice && p.getPrice()<=toPrice)).collect(toList());
    }

    //    As a customer, I can combine multiple filters.
    @GetMapping("/get/combined/{brand}/{color}/{fromPrice}/{toPrice}")
    public List<Product> getResultsByBrandAndColor(@PathVariable String brand, @PathVariable String color, @PathVariable double fromPrice, @PathVariable double toPrice) {
        List<Product> combinedResults = new ArrayList<>();
        combinedResults.addAll(this.getResultsByBrand(brand));
        combinedResults.addAll(this.getResultsByColor(color) );
        combinedResults.addAll(this.getResultsByPriceRange(fromPrice, toPrice));
        return combinedResults;
    }

    //    As a customer, I can order my search results by price, ascending.
    @GetMapping("/get/price/asc")
    public List<Product> getResultsByBSortedPriceAscending(){
        return productRepository.getProductlist().stream().sorted(comparing(Product::getPrice)).collect(toList());
    }

    //    As a customer, I can order my search results by price, descending.
    @GetMapping("/get/price/desc")
    public List<Product> getResultsByBSortedPriceDescending(@PathVariable String brand){
        return productRepository.getProductlist().stream().sorted(comparing(Product::getPrice).reversed()).collect(toList());
    }
}
