package com.example.enigmashop.specification;

import com.example.enigmashop.dto.search.SearchInventoryDTO;
import com.example.enigmashop.entities.Inventory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class InventorySpecification {
    public static Specification<Inventory> getSpecification(SearchInventoryDTO searchInventoryDTO){
        return  new Specification<Inventory>() {
            @Override
            public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(searchInventoryDTO.getIsDeletedFalse() == null){
                    Predicate getDeletedFalse = criteriaBuilder.isFalse(root.get("delete").as(Boolean.class));
                    predicates.add(getDeletedFalse);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
