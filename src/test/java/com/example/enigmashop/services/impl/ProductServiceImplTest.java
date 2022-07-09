package com.example.enigmashop.services.impl;

import com.example.enigmashop.dto.search.SearchProductDTO;
import com.example.enigmashop.entities.Product;
import com.example.enigmashop.exception.DataNotFoundException;
import com.example.enigmashop.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class,SpringExtension.class})
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    private Product product;

//    @BeforeEach
//    void setup(){product = new Product("s123", "Zinc", "Shampoo Zinc", FALSE);
//    }

    @AfterEach
    void cleanUp(){product = new Product();}

    @Test
    void saveProduct() {
        productServiceImpl.saveProduct(product);
        verify(productRepository, times(1)).save(product);
    }

    // Positive test case
    @Test
    void getProductById() {
        when(productRepository.findById("s123")).thenReturn(Optional.of(product));
        Product product1 = productServiceImpl.getProductById("s123");
        assertEquals("s123", product1.getId());

    }

    // Negative test case
    @Test
    void getProductByIdNotFound(){
        Throwable ex = assertThrows(DataNotFoundException.class, () -> productServiceImpl.getProductById("s123"));
        assertEquals("Resource member with ID s123 Not Found", ex.getMessage());
    }

    @Test
    void deleteProduct() {
        product.setDeleted(TRUE);
        when(productRepository.findById("s123")).thenReturn(Optional.of(product));
        productServiceImpl.deleteProduct("s123");
    }

    @Test
    void getProductPerPage() {

        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
        SearchProductDTO searchProductDTO;

        Page<Product> productPage = productRepository.findAll(pageable);
        when(productRepository.findAll(pageable)).thenReturn(productPage);

//        Page<Product> productPage1 = productServiceImpl.getProductPerPage(pageable, );

//        assertEquals(productPage, productPage1);
    }
}