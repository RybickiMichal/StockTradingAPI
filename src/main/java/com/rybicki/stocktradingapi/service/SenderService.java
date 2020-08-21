package com.rybicki.stocktradingapi.service;

import com.rybicki.stocktradingapi.model.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class SenderService {

    @Autowired
    private KafkaTemplate<String, OrderDTO> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    public void send(OrderDTO orderDTO) {
        ListenableFuture<SendResult<String, OrderDTO>> future
                = kafkaTemplate.send(kafkaTopic, orderDTO);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, OrderDTO> result) {
                log.info("Sent message: " + orderDTO.toString() + " with offset: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message : " + orderDTO.toString(), ex);
            }
        });


    }
}