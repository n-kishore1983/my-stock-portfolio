package com.learning.repositories;

import com.learning.entities.Portfolio;
import com.learning.entities.PortfolioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, PortfolioId> {

    List<Portfolio> findByCustomerId(Long customerId);

    List<Portfolio> findByStockSymbol(String stockSymbol);

    List<Portfolio> findByCustomerName(String customerName);
}

