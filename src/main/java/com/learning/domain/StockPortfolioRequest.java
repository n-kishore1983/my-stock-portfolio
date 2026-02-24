package com.learning.domain;

import org.springframework.ai.tool.annotation.ToolParam;

public record StockPortfolioRequest(@ToolParam(description = "Customer Id of the customer") String customerId) {
}
