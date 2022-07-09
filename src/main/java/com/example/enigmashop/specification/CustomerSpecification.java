package com.example.enigmashop.specification;

import com.example.enigmashop.dto.search.SearchCustomerDTO;
import com.example.enigmashop.entities.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerDTO searchCustomerDTO){
        return new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (searchCustomerDTO.getDeletedFalse() == null){
                    Predicate getCustomerDeletedFalse = criteriaBuilder.isFalse(root.get("deleted").as(Boolean.class));
                    predicates.add(getCustomerDeletedFalse);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
