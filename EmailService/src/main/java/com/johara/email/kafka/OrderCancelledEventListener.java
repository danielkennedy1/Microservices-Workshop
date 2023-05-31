package com.johara.email.kafka;

import com.johara.email.model.OrderMessage;
import com.johara.email.service.EmailSendingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelledEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCancelledEventListener.class);

    private final EmailSendingService emailSendingService;
    @Autowired
    public OrderCancelledEventListener(EmailSendingService emailSendingService) {
        this.emailSendingService = emailSendingService;
    }

    @KafkaListener(topics = "order-cancelled-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderPlacedEvent(OrderMessage orderMessage) {
        LOGGER.info("Received OrderCancelledEvent for order: {}", orderMessage.getOrderId());

        // Call the email service to send the email
        emailSendingService.sendOrderCancelledEmail(orderMessage);
    }
}
