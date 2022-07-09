package com.example.enigmashop.repositories;

import com.example.enigmashop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor {
    Optional<Customer> findCustomerByEmail(String email);
    Boolean existsCustomerByEmail(String email);

}
