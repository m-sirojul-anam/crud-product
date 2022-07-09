package com.example.enigmashop.services;

import com.example.enigmashop.dto.search.SearchStoreDTO;
import com.example.enigmashop.entities.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService {
    public Store saveStore(Store store);
    public Store getStoreById(String id);
    public Store deleteStore(String id);
    public Page<Store> getStorePerPage(Pageable pageable, SearchStoreDTO searchStoreDTO);
}
