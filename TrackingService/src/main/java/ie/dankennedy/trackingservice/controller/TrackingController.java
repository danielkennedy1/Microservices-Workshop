package ie.dankennedy.trackingservice.controller;

import ie.dankennedy.trackingservice.model.Identity;
import ie.dankennedy.trackingservice.model.IdentityService;
import ie.dankennedy.trackingservice.repository.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackingController {

    private final IdentityService identityService;
    private final IdentityRepository IdentityRepository;

    @Autowired
    public TrackingController(IdentityService identityService, IdentityRepository IdentityRepository) {
        this.identityService = identityService;
        this.IdentityRepository = IdentityRepository;
    }


    @GetMapping("/newidentity")
    public String getTrackingPage() {
        System.out.println("In TrackingController: Generating new identity");
        Identity identity = identityService.buildIdentity();
        System.out.println("In TrackingController: Generated new identity: " + identity);
        return identity.getId();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Identity>> getAllIdentities() {
        List<Identity> identities = IdentityRepository.findAll();
        return ResponseEntity.ok(identities);
    }
}
