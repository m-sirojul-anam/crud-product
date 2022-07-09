package com.example.enigmashop.services;

import com.example.enigmashop.dto.search.SearchCustomerDTO;
import com.example.enigmashop.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(String id);
    Customer deleteCustomer(String id);
    Page<Customer> getCustomerPerPage(Pageable pageable, SearchCustomerDTO searchCustomerDTO);
    List<Customer> getCustomerByName(String firstName, String lastName);
    List<Customer> getActiveCustomer();
    List<Customer> getNonActiveCustomer(String firstName, String lastName);
    void updateCustomerStatus(String id);
}
