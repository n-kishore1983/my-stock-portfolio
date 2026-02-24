package com.learning.tools;

import com.learning.domain.StockPriceRequest;
import com.learning.domain.StockPriceResponse;
import com.learning.entities.Portfolio;
import com.learning.repositories.PortfolioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
public class ToolsService {

    @Value("${tickr.api.key}")
    private String tickrApiKey;

    @Value("${tickr.api.url}")
    private String tickrApiUrl;

    private final PortfolioRepository portfolioRepository;
    private final RestClient.Builder restClientBuilder;

    public ToolsService(PortfolioRepository portfolioRepository, RestClient.Builder restClientBuilder) {
        this.portfolioRepository = portfolioRepository;
        this.restClientBuilder = restClientBuilder;
    }


    @Tool(name = "get_customer_stocks", description = "Get all the portfolios for a given customer")
    public List<Portfolio> getPortfoliosForCustomer(Long customerId) {
        log.info("Getting portfolios for customer {}", customerId);
        return portfolioRepository.findByCustomerId(customerId);
    }

    @Tool(name = "get_stock_details", description = "Get stock price details for a ticker symbol")
    public StockPriceResponse getStockDetails(StockPriceRequest stockPriceRequest) {
        log.info("Fetching stock details for ticker {}", stockPriceRequest.ticker());
        return restClientBuilder
                .baseUrl(tickrApiUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stockprice")
                        .queryParam("ticker", stockPriceRequest.ticker())
                        .build())
                .header("X-Api-Key", tickrApiKey)
                .retrieve()
                .body(StockPriceResponse.class);
    }

}
