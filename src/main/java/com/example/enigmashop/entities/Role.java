package com.example.enigmashop.entities;

import com.example.enigmashop.constant.AppUserRole;
import com.example.enigmashop.constant.ERole;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mst_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;
}
