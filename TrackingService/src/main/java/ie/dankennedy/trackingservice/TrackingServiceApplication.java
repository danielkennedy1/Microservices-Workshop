package ie.dankennedy.trackingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient // This is the annotation that enables the service to register with Eureka
public class TrackingServiceApplication {

    public static void main(String[] args) {

        System.setProperty("spring.config.name", "tracking-service");
        SpringApplication.run(TrackingServiceApplication.class, args);
    }

}
