package com.example.enigmashop.services;

import com.example.enigmashop.dto.search.SearchProductDTO;
import com.example.enigmashop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public Product getProductById(String id);
    public List<Product> getProductName(String name);
    public Product deleteProduct(String id);
    public Page<Product> getProductPerPage(Pageable pageable, SearchProductDTO searchInventoryDTO);
    public Product saveProductPicture(Product product, MultipartFile file);
}
