package com.example.enigmashop.services.impl;

import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.search.SearchCustomerDTO;
import com.example.enigmashop.entities.Customer;
import com.example.enigmashop.exception.DataNotFoundException;
import com.example.enigmashop.repositories.CustomerRepository;
import com.example.enigmashop.services.CustomerService;
import com.example.enigmashop.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer deleteCustomer(String id) {
        Customer customer = customerRepository.findById(id).get();
        customer.setDeleted(TRUE);
        return saveCustomer(customer);
    }

    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, SearchCustomerDTO searchCustomerDTO) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(searchCustomerDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public List<Customer> getCustomerByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public List<Customer> getActiveCustomer() {
        return null;
    }

    @Override
    public List<Customer> getNonActiveCustomer(String firstName, String lastName) {
        return null;
    }

    @Override
    public void updateCustomerStatus(String id) {

    }

    private void validatePresent(String id) {
        if (!customerRepository.findById(id).isPresent()){
            String message = String.format(ResponseMessage.DATA_NOT_FOUND, "customer", id);
            throw new DataNotFoundException(message);
        }
    }

    public Boolean userNameExist(String username){
        return customerRepository.existsCustomerByEmail(username);
    }
}
