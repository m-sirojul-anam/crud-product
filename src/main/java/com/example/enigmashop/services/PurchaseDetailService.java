package com.example.enigmashop.services;

import com.example.enigmashop.entities.PurchaseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseDetailService {
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail);
    public PurchaseDetail getPurchaseDetailById(String id);
    public void deletePurchase(String id);
    public Page<PurchaseDetail> getPurchaseDetailPerPage(Pageable pageable);
}
