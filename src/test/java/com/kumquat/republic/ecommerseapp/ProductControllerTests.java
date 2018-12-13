package com.kumquat.republic.ecommerseapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumquat.republic.ecommerseapp.models.Product;
import com.kumquat.republic.ecommerseapp.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private List<Product> testProductList;

    @MockBean
    ProductRepository productRepository;

    @Before
    public void setup() {

        testProductList = Arrays.asList(
                    new Product("dresses", "Gucci", 	"yellow", 10),
                    new Product("dresses", "Mark Jacobs", 	"Red", 25.00),
                    new Product("dresses", "Sabyasachi", 	"Blue", 2262.00),
                    new Product("dresses", "Gucci", 	"cerulean", 2272.00));
    }

    @Test
    public void test_getResultsByBrand() throws Exception {

        when(productRepository.getProductlist()).thenReturn(testProductList);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/get/byBrand/Gucci")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Gucci")));

        verify(productRepository, times(1)).getProductlist();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void test_getResultsByColor() throws Exception {

        when(productRepository.getProductlist()).thenReturn(testProductList);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/get/byColor/yellow")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].price", is(10.0)))
                .andDo(print())
                .andReturn();

        verify(productRepository, times(1)).getProductlist();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void test_getResultsByPriceRange() throws Exception {

        when(productRepository.getProductlist()).thenReturn(testProductList);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/get/price/{fromPrice}/{toPrice}", 20, 30)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();

        verify(productRepository, times(1)).getProductlist();
        verifyNoMoreInteractions(productRepository);
    }
}
