package com.johara.product.client;

import com.johara.product.model.IdentityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrackingServiceClient {
    private static final String TRACKING_SERVICE_NAME = "tracking-service";
    private static final String TRACKING_ENDPOINT = "/newidentity";

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    @Autowired
    public TrackingServiceClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = new RestTemplate();
    }

    public IdentityDTO createIdentity() {
        ServiceInstance trackingInstance = getServiceInstance();
        String trackingUrl = trackingInstance.getUri().toString() + TRACKING_ENDPOINT;
        String newId = restTemplate.getForEntity(trackingUrl, String.class).getBody();
        System.out.println("New ID: " + newId);
        return new IdentityDTO(newId, 1F);
    }

    private ServiceInstance getServiceInstance() {
        return discoveryClient.getInstances(TRACKING_SERVICE_NAME)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tracking Service not found"));
    }
}
