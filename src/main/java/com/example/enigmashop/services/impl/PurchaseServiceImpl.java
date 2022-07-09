package com.example.enigmashop.services.impl;

import com.example.enigmashop.entities.Customer;
import com.example.enigmashop.entities.Purchase;
import com.example.enigmashop.entities.PurchaseDetail;
import com.example.enigmashop.repositories.PurchaseRepository;
import com.example.enigmashop.services.CustomerService;
import com.example.enigmashop.services.PurchaseDetailService;
import com.example.enigmashop.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Purchase savePurchase(Purchase purchase) {
        Purchase purchaseValue = purchaseRepository.save(purchase);

        int grandTotal = 0;
        BigDecimal amount = new BigDecimal(0.0);
        for(PurchaseDetail purchaseDetail : purchaseValue.getPurchaseDetailSet()){
            purchaseDetail.setPurchase(purchaseValue);
            purchaseDetailService.savePurchaseDetail(purchaseDetail);
            Integer quantity = purchaseDetail.getQuantity();
            Double price = Double.valueOf(purchaseDetail.getInventory().getProductPrice());
            Double subtotal = price * quantity;
            amount = amount.add(BigDecimal.valueOf(subtotal));
            grandTotal += purchaseDetail.getProductPriceSell();
        }
        Customer customer = customerService.getCustomerById(purchaseValue.getCustomer().getId());

        purchaseValue.setGrandTotal(BigDecimal.valueOf(grandTotal));
        purchaseValue.setCustomer(customer);

        String url = "http://localhost:8090/debit";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("phoneNumber", customer.getPhone())
                .queryParam("amount", amount);

        restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, String.class);
        return purchaseValue;
    }

    @Override
    public Purchase getPurchaseById(String id) {
        return purchaseRepository.findById(id).get();
    }

    @Override
    public Page<Purchase> getPurchasePerPage(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }
}
