package com.kumquat.republic.ecommerseapp.repository;

import com.kumquat.republic.ecommerseapp.models.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> products;

    public List<Product> getProductlist() {
        products = Arrays.asList(
                new Product("dresses", "Gucci", 	"yellow", 2272.00),
                new Product("dresses", "Mark Jacobs", 	"Red", 72.00),
                new Product("dresses", "Sabyasachi", 	"Blue", 2262.00));

        return Collections.unmodifiableList(products);
    }
}
