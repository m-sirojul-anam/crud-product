package com.example.enigmashop.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "trx_purchase")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Purchase {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private Date purchaseDate = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));

    private BigDecimal grandTotal;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetail> purchaseDetailSet = new ArrayList<>();
}
