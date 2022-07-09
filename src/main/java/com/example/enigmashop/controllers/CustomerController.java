package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.search.SearchCustomerDTO;
import com.example.enigmashop.entities.Customer;
import com.example.enigmashop.services.CustomerService;
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
@RequestMapping(ApiConstantURL.CUSTOMER)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Response<Customer>> saveCustomer(@RequestBody Customer customer){

        Response<Customer> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERT, customer.getFirstName());
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Customer>> getCustomerById(@PathVariable String id){

        Response<Customer> response = new Response<>();
        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(customerService.getCustomerById(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<Response<Customer>> updateCustomer(@RequestBody Customer customer){

        Response<Customer> response = new Response<>();
        String message = ResponseMessage.SUCCESS_UPDATED;
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Customer>> deleteCustomer(@PathVariable String id){

        Response<Customer> response = new Response<>();
        String message = ResponseMessage.SUCCESS_DELETED;
        response.setMessage(message);
        response.setData(customerService.deleteCustomer(id));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Response<Page<Customer>>> getCustomerPerPage(
            SearchCustomerDTO searchCustomerDTO,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer sizePerPage,
            Sort sort){

        Response<Page<Customer>> response = new Response<>();
        Pageable pageable = PageRequest.of(page,sizePerPage,sort);

        String message = ResponseMessage.SUCCESS;
        response.setMessage(message);
        response.setData(customerService.getCustomerPerPage(pageable, searchCustomerDTO));

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
