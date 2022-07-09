package com.example.enigmashop.services.impl;

import com.example.enigmashop.dto.search.SearchStoreDTO;
import com.example.enigmashop.entities.Store;
import com.example.enigmashop.repositories.StoreRepository;
import com.example.enigmashop.services.StoreService;
import com.example.enigmashop.specification.StoreSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.TRUE;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getStoreById(String id) {
        return storeRepository.findById(id).get();
    }

    @Override
    public Store deleteStore(String id) {
        Store store = storeRepository.findById(id).get();
        store.setDelete(TRUE);
        return saveStore(store);
    }

    @Override
    public Page<Store> getStorePerPage(Pageable pageable, SearchStoreDTO searchStoreDTO) {
        Specification<Store> storeSpecification = StoreSpecification.getSpecification(searchStoreDTO);
        return storeRepository.findAll(storeSpecification,pageable);
    }
}
