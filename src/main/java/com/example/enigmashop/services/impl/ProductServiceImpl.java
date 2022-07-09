package com.example.enigmashop.services.impl;

import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.search.SearchProductDTO;
import com.example.enigmashop.entities.Product;
import com.example.enigmashop.exception.DataNotFoundException;
import com.example.enigmashop.repositories.ProductRepository;
import com.example.enigmashop.services.ProductService;
import com.example.enigmashop.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        if(!(productRepository.findById(id).isPresent())){
            String message = String.format(ResponseMessage.DATA_NOT_FOUND, "Product", id);
            throw new DataNotFoundException(message);
        }
            return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product deleteProduct(String id) {
        Product product = productRepository.findById(id).get();
        product.setDeleted(TRUE);
        return productRepository.save(product);
    }

//    @Override
//    public Page<Product> getProductPerPage(Pageable pageable, SearchProductDTO searchProductDTO) {
//        Specification<Product> productSpecification = ProductSpecification.getSpecification(searchProductDTO);
//        return productRepository.findAll(
//                productSpecification, pageable);
//    }

    @Override
    public Page<Product> getProductPerPage(Pageable pageable, SearchProductDTO searchProductDTO) {
        Specification<Product> productSpecification = ProductSpecification.getSpecification(searchProductDTO);
        return productRepository.findAll(productSpecification,pageable);
    }

    @Override
    public Product saveProductPicture(Product product, MultipartFile file) {
        String path = "D:\\07-New Project\\CRUD Product\\enigmashop\\src\\main\\java\\com\\example\\enigmashop\\assets";
        Path pathFolder = Paths.get(path);
        Path pathFile = Paths.get(pathFolder.toString() + "/" + file.getOriginalFilename() + ".png");
        try{
            file.transferTo(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setProductImage(file.getOriginalFilename());
        return productRepository.save(product);
    }
}
