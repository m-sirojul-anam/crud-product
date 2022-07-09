package com.example.enigmashop.specification;

import com.example.enigmashop.dto.search.SearchProductDTO;
import com.example.enigmashop.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductSpecification {
    public static Specification<Product> getSpecification(SearchProductDTO searchProductDTO){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (searchProductDTO.getSearchProductName() != null){
                    Predicate getProductNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchProductDTO.getSearchProductName().toLowerCase(Locale.ROOT) + "%");
                    predicates.add(getProductNamePredicate);
                }

                if (searchProductDTO.getDeletedFalse() == null){
                    Predicate getProductDeletedFalse = criteriaBuilder.isFalse(root.get("deleted").as(Boolean.class));
                    predicates.add(getProductDeletedFalse);
                }

                Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);
                return criteriaBuilder.and(arrayPredicate);
            }
        };
    }
}
