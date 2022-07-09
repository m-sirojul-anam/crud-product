package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.search.SearchProductDTO;
import com.example.enigmashop.entities.Product;
import com.example.enigmashop.services.ProductService;
import com.example.enigmashop.utils.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiConstantURL.PRODUCT)
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Response<Product>> saveProduct(@RequestBody Product product){

        Response<Product> response = new Response<>();

        String message = String.format(ResponseMessage.DATA_INSERT, "Book Return Record");
        response.setMessage(message);
        response.setData(productService.saveProduct(product));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Product>> getProductById(@PathVariable String id){
        Response<Product> response = new Response<>();

        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(productService.getProductById(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Product>> updateProduct(@RequestBody Product product){

        Response<Product> response = new Response<>();
        String message = ResponseMessage.SUCCESS_UPDATED;
        response.setMessage(message);
        response.setData(productService.saveProduct(product));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Product>> deleteProduct(@PathVariable String id){

        Response<Product> response = new Response<>();
        String message = ResponseMessage.SUCCESS_DELETED;
        response.setMessage(message);
        response.setData(productService.deleteProduct(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Response<Page<Product>>> getProductPerPage(
            SearchProductDTO searchProductDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ){
        Response<Page<Product>> response = new Response<>();

        String message = ResponseMessage.SUCCESS;
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);

        response.setMessage(message);
        response.setData(productService.getProductPerPage(pageable, searchProductDTO));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PostMapping("/picture")
    public void registerProductPicture(@RequestPart(name = "picture", required = false)MultipartFile file,
                                       @RequestPart(name = "product") String product){
        Product product1 = new Product();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product1 = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        productService.saveProductPicture(product1, file);
    }
}
