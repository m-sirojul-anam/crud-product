package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.search.SearchStoreDTO;
import com.example.enigmashop.entities.Store;
import com.example.enigmashop.services.StoreService;
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
@RequestMapping(ApiConstantURL.STORE)
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping
    public ResponseEntity<Response<Store>> saveStore(@RequestBody Store store){

        Response<Store> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERT, store.getName());
        response.setMessage(message);
        response.setData(storeService.saveStore(store));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Store>> getStoreById(@PathVariable String id){

        Response<Store> response = new Response<>();
        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(storeService.getStoreById(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Store>> updateStore(@RequestBody Store store){

        Response<Store> response = new Response<>();
        String message = ResponseMessage.SUCCESS_UPDATED;
        response.setMessage(message);
        response.setData(storeService.saveStore(store));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Store>> deleteStore(@PathVariable String id){

        Response<Store> response = new Response<>();
        String message = ResponseMessage.SUCCESS_DELETED;
        response.setMessage(message);
        response.setData(storeService.deleteStore(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Response<Page<Store>>> getStorePerPage(
            SearchStoreDTO searchStoreDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ){

        Response<Page<Store>> response = new Response<>();
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(storeService.getStorePerPage(pageable, searchStoreDTO));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
