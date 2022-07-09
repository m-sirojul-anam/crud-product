package com.example.enigmashop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static java.lang.Boolean.FALSE;

@Getter
@Setter
@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Inventory {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Integer productPrice;

    private Integer stock;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean delete = FALSE;

    @ManyToOne
    @JoinColumn
    private Store store;

    @ManyToOne
    @JoinColumn
    private Product product;
}
