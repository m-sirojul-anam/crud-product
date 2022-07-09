package com.example.enigmashop.services.impl;

import com.example.enigmashop.dto.search.SearchInventoryDTO;
import com.example.enigmashop.entities.Inventory;
import com.example.enigmashop.entities.Product;
import com.example.enigmashop.entities.Store;
import com.example.enigmashop.repositories.InventoryRepository;
import com.example.enigmashop.services.InventoryService;
import com.example.enigmashop.services.ProductService;
import com.example.enigmashop.services.StoreService;
import com.example.enigmashop.specification.InventorySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    StoreService storeService;

    @Autowired
    ProductService productService;

    @Override
    public Inventory saveInventory(Inventory inventory) {

        String storeId = inventory.getStore().getId();
        String productId = inventory.getProduct().getId();

        Store store = storeService.getStoreById(storeId);
        Product product = productService.getProductById(productId);

        inventory.setStore(store);
        inventory.setProduct(product);

        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory getInventoryById(String id) {
        return inventoryRepository.findById(id).get();
    }

    @Override
    public Inventory deleteInventory(String id) {
        Inventory inventory = inventoryRepository.findById(id).get();
        inventory.setDelete(TRUE);

        return saveInventory(inventory);
    }

    @Override
    public Page<Inventory> getInventoryPerPage(Pageable pageable, SearchInventoryDTO searchInventoryDTO) {
        Specification<Inventory> inventorySpecification = InventorySpecification.getSpecification(searchInventoryDTO);
        return inventoryRepository.findAll(inventorySpecification, pageable);
    }
}
