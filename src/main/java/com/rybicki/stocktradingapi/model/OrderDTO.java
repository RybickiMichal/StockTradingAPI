package com.rybicki.stocktradingapi.model;

import lombok.Getter;

@Getter
public class OrderDTO {
    private OrderType orderType;
    private String userId;
    private String companyIndex;
    private Money money;
}
