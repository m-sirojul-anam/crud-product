package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.entities.Product;
import com.example.enigmashop.services.ProductService;
import com.example.enigmashop.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;

import static java.lang.Boolean.FALSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ExtendWith({MockitoExtension.class})
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void saveProduct() {
        Product product = new Product("s123", "Zinc", "Shampoo Zinc","", FALSE);
        when(productService.saveProduct(any(Product.class))).thenReturn(product);
        ResponseEntity<Response<Product>> product1 = productController.saveProduct(product);
        assertThat(product1.getBody().getData().getName()).isEqualTo("Zinc");
    }

    @Test
    void saveProductWithHeaderStatus400() throws Exception{
        this.mockMvc.perform(post(ApiConstantURL.PRODUCT)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createProductWithResponseHeader() throws Exception{
        given(productService.saveProduct(any(Product.class))).willAnswer(invocationOnMock -> {
         return invocationOnMock.getArgument(0);
        });
        Product product = new Product("s123", "Zinc", "Zinc Bae","", FALSE);
        this.mockMvc.perform(post(ApiConstantURL.PRODUCT)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(product)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertThat(jsonPath("data.name", Matchers.is(product.getName())));
    }

    public static String asJsonString(final Object object){
        try {
         return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getProductById() {
    }

    @Test
    void updateProduct() {
    }


}