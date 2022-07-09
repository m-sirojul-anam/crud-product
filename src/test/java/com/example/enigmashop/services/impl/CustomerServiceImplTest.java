package com.example.enigmashop.services.impl;

import com.example.enigmashop.entities.Customer;
import com.example.enigmashop.entities.Product;
import com.example.enigmashop.repositories.CustomerRepository;
import com.example.enigmashop.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void saveCustomer() {
        Customer customer = new Customer("s123","Samuel","Ojul","L", Date.valueOf("2001-01-20"),"Jl. Bangsa Indonesia","0835255","ojul@gmail.com",0, "123",FALSE);
        customerService.saveCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer("s123","Samuel","Ojul","L", Date.valueOf("2001-01-20"),"Jl. Bangsa Indonesia","0835255","ojul@gmail.com",0, "123",FALSE);
        when(customerRepository.findById("s123")).thenReturn(Optional.of(customer));
        Customer customer1 = customerService.getCustomerById("s123");
        assertEquals("s123", customer1.getId());
    }

    @Test
    void deleteCustomer() {
        Customer customer = new Customer("s123","Samuel","Ojul","L", Date.valueOf("2001-01-20"),"Jl. Bangsa Indonesia","0835255","ojul@gmail.com",0, "123",FALSE);
        customer.setDeleted(TRUE);
        when(customerRepository.findById("s123")).thenReturn(Optional.of(customer));
        customerService.deleteCustomer("s123");
    }

//    @Test
//    @ExtendWith(SpringExtension.class)
//    void getCustomerPerPage() {
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("id"));
//
//        Page<Customer> customerPage = customerRepository.findAll(pageable);
//        when(customerRepository.findAll(pageable)).thenReturn(customerPage);
//
//        Page<Customer> customerPage1 = customerService.getCustomerPerPage(pageable);
//        assertEquals(customerRepository.findAll(pageable), customerPage1);
//    }
}