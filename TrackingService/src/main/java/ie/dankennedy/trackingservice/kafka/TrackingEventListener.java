package ie.dankennedy.trackingservice.kafka;

import ie.dankennedy.trackingservice.model.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class TrackingEventListener {

    private final IdentityService identityService;

    @Autowired
    public TrackingEventListener(IdentityService identityService) {
        this.identityService = identityService;
    }

    @KafkaListener(topics = "tracking", groupId = "${spring.kafka.consumer.group-id}")
    public void handleTrackingEvent(@Payload(required = false) String id) {
        System.err.println("Received tracking event for id: " + id);
        identityService.incrementIdentity(id);
    }
}
