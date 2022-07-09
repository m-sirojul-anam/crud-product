package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.entities.PurchaseDetail;
import com.example.enigmashop.services.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstantURL.PURCHASE)
public class PurchaseDetailController {

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @PostMapping("/detail")
    public PurchaseDetail savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail){
        return purchaseDetailService.savePurchaseDetail(purchaseDetail);
    }

    @GetMapping("detail/{id}")
    public PurchaseDetail getPurchaseDetailById(@PathVariable String id){
        return purchaseDetailService.getPurchaseDetailById(id);
    }
}
