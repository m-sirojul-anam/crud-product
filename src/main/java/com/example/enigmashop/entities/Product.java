package com.example.enigmashop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Objects;

import static java.lang.Boolean.FALSE;

@Getter
@Setter
@Entity
@Table(name = "mst_product")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;
    private String description;
    private String productImage;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean deleted = FALSE;

}
