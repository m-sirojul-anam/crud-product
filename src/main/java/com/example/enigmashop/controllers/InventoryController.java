package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.search.SearchInventoryDTO;
import com.example.enigmashop.entities.Inventory;
import com.example.enigmashop.services.InventoryService;
import com.example.enigmashop.utils.Response;
import io.swagger.models.auth.In;
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
@RequestMapping(ApiConstantURL.INVENTORY)
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Response<Inventory>> saveInventory(@RequestBody Inventory inventory){

        Response<Inventory> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERT, inventory.getProduct().getName());
        response.setMessage(message);
        response.setData(inventoryService.saveInventory(inventory));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Inventory>> getInventoryById(@PathVariable String id){

        Response<Inventory> response = new Response<>();
        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(inventoryService.getInventoryById(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Inventory>> updateInventory(@RequestBody Inventory inventory){

        Response<Inventory> response = new Response<>();
        String message = ResponseMessage.SUCCESS_UPDATED;
        response.setMessage(message);
        response.setData(inventoryService.saveInventory(inventory));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Inventory>> deleteInventory(@PathVariable String id){

        Response<Inventory> response = new Response<>();
        String message = ResponseMessage.SUCCESS_DELETED;
        response.setMessage(message);
        response.setData(inventoryService.deleteInventory(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Response<Page<Inventory>>> getInventoryPerPage(
            SearchInventoryDTO searchInventoryDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort
    ){

        Response<Page<Inventory>> response = new Response<>();
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(inventoryService.getInventoryPerPage(pageable, searchInventoryDTO));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
