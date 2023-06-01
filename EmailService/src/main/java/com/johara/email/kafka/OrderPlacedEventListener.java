package com.johara.email.kafka;

import com.johara.email.model.OrderMessage;
import com.johara.email.service.EmailSendingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPlacedEventListener.class);

    private final EmailSendingService emailSendingService;
    @Autowired
    public OrderPlacedEventListener(EmailSendingService emailSendingService) {
        this.emailSendingService = emailSendingService;
    }

    @KafkaListener(topics = "orders", groupId = "${spring.kafka.consumer.group-id}")
    public void handleOrderUpdateEvent(OrderMessage orderMessage) {
        LOGGER.info("Received OrderUpdateEvent for order: {}", orderMessage.getOrderId());
        LOGGER.info("Status of order: {}", orderMessage.getOrderStatus());

        if(orderMessage.getOrderStatus().equals("CANCELLED")) {
            // Call the email service to send the email
            emailSendingService.sendOrderCancelledEmail(orderMessage);
            return;
        }else if (!orderMessage.getOrderStatus().equals("COMPLETED")) {
            LOGGER.info("Order status is not COMPLETED or CANCELLED. No email will be sent");
            return;
        }

        // Unknow order status
        LOGGER.info("Order status is not COMPLETED or CANCELLED. No email will be sent");
    }
}
