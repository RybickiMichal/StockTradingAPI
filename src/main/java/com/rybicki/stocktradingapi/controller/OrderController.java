package com.rybicki.stocktradingapi.controller;

import com.rybicki.stocktradingapi.model.OrderDTO;
import com.rybicki.stocktradingapi.service.SenderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {

    private SenderService senderService;

    @PostMapping(value = "/order/create")
    public String createOrder(@RequestBody OrderDTO orderDTO) {
        senderService.send(orderDTO);
        return orderDTO.getOrderType().getType() + " order sent to consumers";
    }
}
