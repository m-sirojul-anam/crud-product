package com.example.enigmashop.dto.search;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchProductDTO {
    private String searchProductName;
    private Boolean deletedFalse;
}
