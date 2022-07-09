package com.example.enigmashop.services;


import com.example.enigmashop.dto.search.SearchInventoryDTO;
import com.example.enigmashop.entities.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {
    public Inventory saveInventory(Inventory inventory);
    public Inventory getInventoryById(String id);
    public Inventory deleteInventory(String id);
    public Page<Inventory> getInventoryPerPage(Pageable pageable, SearchInventoryDTO searchInventoryDTO);
}
