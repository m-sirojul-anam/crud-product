package com.example.enigmashop.dto.search;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchInventoryDTO {
    private Boolean isDeletedFalse;
}
