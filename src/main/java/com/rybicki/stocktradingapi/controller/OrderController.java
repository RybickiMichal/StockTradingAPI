package com.rybicki.stocktradingapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {

    private KafkaTemplate<String, String> kafkaTemplate;

    //TODO change params into DTO and Mapping to POST
    @GetMapping(value = "/order/create/{orderType}/{userId}/{companyIndex}/{currency}/{amount}")
    public String createOrderForBuyStocks(@PathVariable String orderType, @PathVariable String userId, @PathVariable String companyIndex,
                                          @PathVariable String currency, @PathVariable String amount) {
        kafkaTemplate.send("Orders", orderType + "," + userId + "," + companyIndex + "," + currency + "," + amount);
        return "Buy order sent to consumers";
    }

    @GetMapping(value = "/order/create/{orderType}/{userId}/{companyIndex}")
    public String createOrderForSellStocks(@PathVariable String orderType, @PathVariable String userId, @PathVariable String companyIndex) {
        kafkaTemplate.send("Orders", orderType + "," + userId + "," + companyIndex);
        return "Sell order sent to consumers";
    }

}
