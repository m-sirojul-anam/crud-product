package com.example.enigmashop.specification;

import com.example.enigmashop.dto.search.SearchStoreDTO;
import com.example.enigmashop.entities.Store;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StoreSpecification {
    public static Specification<Store> getSpecification(SearchStoreDTO searchStoreDTO){
        return new Specification<Store>() {
            @Override
            public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (searchStoreDTO.getDeletedFalse()==null){
                    Predicate getStoreDeleteFalse = criteriaBuilder.isFalse(root.get("delete").as(Boolean.class));
                    predicates.add(getStoreDeleteFalse);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
