package com.example.enigmashop.services.impl;

import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.entities.Inventory;
import com.example.enigmashop.entities.PurchaseDetail;
import com.example.enigmashop.exception.DataOutOfStockException;
import com.example.enigmashop.repositories.PurchaseDetailRepository;
import com.example.enigmashop.services.InventoryService;
import com.example.enigmashop.services.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    @Autowired
    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    InventoryService inventoryService;

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {

        Integer getPurchaseDetailQuantity = purchaseDetail.getQuantity();
        String getInventoryId = purchaseDetail.getInventory().getId();

        Inventory inventory = inventoryService.getInventoryById(getInventoryId);
        Integer getInventoryStock = inventory.getStock();

        if(getInventoryStock <= 0){

            String message = String.format(ResponseMessage.DATA_OUT_OF_STOCK, "Product", inventory.getProduct().getName());
            throw  new DataOutOfStockException(message);

        } else {

            inventory.setStock(getInventoryStock - getPurchaseDetailQuantity);
            Integer productPrice = inventory.getProductPrice();

            purchaseDetail.setInventory(inventory);
            purchaseDetail.setProductPriceSell(productPrice*getPurchaseDetailQuantity);

            return purchaseDetailRepository.save(purchaseDetail);
        }
    }

    @Override
    public PurchaseDetail getPurchaseDetailById(String id) {
        return purchaseDetailRepository.findById(id).get();
    }

    @Override
    public void deletePurchase(String id) {
        PurchaseDetail purchaseDetail = getPurchaseDetailById(id);
        purchaseDetailRepository.delete(purchaseDetail);
    }

    @Override
    public Page<PurchaseDetail> getPurchaseDetailPerPage(Pageable pageable) {
        return purchaseDetailRepository.findAll(pageable);
    }
}
