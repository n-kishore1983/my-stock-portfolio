package com.learning.domain;

import org.springframework.ai.tool.annotation.ToolParam;

public record StockPriceRequest(@ToolParam(description = "Tickr symbol for the request") String ticker) {
}
