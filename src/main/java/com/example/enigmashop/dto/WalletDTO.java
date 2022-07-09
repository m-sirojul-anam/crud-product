package com.example.enigmashop.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WalletDTO {
    private String id;
    private String phoneNumber;
    private BigDecimal balance;
}
