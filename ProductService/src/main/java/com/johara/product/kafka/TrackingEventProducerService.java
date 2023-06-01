package com.johara.product.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrackingEventProducerService {
    private static final String TOPIC_NAME = "tracking";

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingEventProducerService.class);

    private final KafkaTemplate<Object, String> kafkaTemplate;

    @Autowired
    public TrackingEventProducerService(KafkaTemplate<Object, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendTrackingEvent(String id) {
        kafkaTemplate.send(TOPIC_NAME, id);
        LOGGER.info("Tracking event sent to Kafka topic: {} with id {}", TOPIC_NAME, id);

    }

}
