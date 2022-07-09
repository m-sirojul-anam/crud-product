package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.entities.Purchase;
import com.example.enigmashop.services.PurchaseService;
import com.example.enigmashop.utils.PageResponseWrapper;
import com.example.enigmashop.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstantURL.PURCHASE)
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Response<Purchase>> savePurchase(@RequestBody Purchase purchase){

        Response<Purchase> response = new Response<>();

        String message = String.format(ResponseMessage.DATA_INSERT, "Purchase");
        response.setMessage(message);

        response.setData(purchaseService.savePurchase(purchase));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable String id){
        return purchaseService.getPurchaseById(id);
    }

    @GetMapping
    public PageResponseWrapper<Purchase> getPurchasePerPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ){

        Pageable pageable = PageRequest.of(page,sizePerPage,sort);

        Page<Purchase> purchasePage = purchaseService.getPurchasePerPage(pageable);

        return new PageResponseWrapper<>(purchasePage);
    }
}
