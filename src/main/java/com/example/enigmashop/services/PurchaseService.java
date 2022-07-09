package com.example.enigmashop.services;

import com.example.enigmashop.entities.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    public Purchase savePurchase(Purchase purchase);
    public Purchase getPurchaseById(String id);
    public Page<Purchase> getPurchasePerPage(Pageable pageable);
}
