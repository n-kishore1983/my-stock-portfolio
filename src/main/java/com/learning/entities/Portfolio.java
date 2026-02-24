package com.learning.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "portfolio")
@IdClass(PortfolioId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Id
    @Column(name = "stock_symbol")
    private String stockSymbol;

    @Column(name = "quantity")
    private Integer quantity;
}

