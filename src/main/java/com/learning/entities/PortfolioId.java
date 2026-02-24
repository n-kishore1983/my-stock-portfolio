package com.learning.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioId implements Serializable {

    private Long customerId;
    private String stockSymbol;
}

