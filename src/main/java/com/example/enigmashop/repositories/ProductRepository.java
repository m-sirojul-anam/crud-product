package com.example.enigmashop.repositories;

import com.example.enigmashop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor {
    // native Query ( value = "SELECT * FROM mst_customer c where c.status = ?1", nativeQuery = true);

    //Jpa Query
    @Query("select p from Product p WHERE p.name= ?1")
    public List<Product> findByName(String name);
}
