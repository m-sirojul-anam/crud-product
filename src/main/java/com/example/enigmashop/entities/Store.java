package com.example.enigmashop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static java.lang.Boolean.FALSE;

@Getter
@Setter
@Entity
@Table(name = "mst_store")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String noSiup;
    private String name;
    private String address;
    private String phone;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean delete = FALSE;
}
