package com.example.enigmashop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "trx_purchase_detail")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Integer productPriceSell;

    private Integer quantity;

    @ManyToOne
    @JoinColumn
    private Inventory inventory;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Purchase purchase;
}
