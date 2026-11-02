package com.main.micvroservice.order_service.dto;

import java.math.BigInteger;

public record OrderRequest(Long id, String orderNumber, String skucode,
                           BigInteger price, Integer quantity) {
}
