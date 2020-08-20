package com.rybicki.stocktradingapi.service;

import com.rybicki.stocktradingapi.model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SenderService {

    @Autowired
    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    public void send(OrderDTO orderDTO) {
        kafkaTemplate.send(kafkaTopic, orderDTO);
    }
}