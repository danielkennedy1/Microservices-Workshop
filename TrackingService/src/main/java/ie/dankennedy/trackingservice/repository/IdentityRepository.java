package ie.dankennedy.trackingservice.repository;

import ie.dankennedy.trackingservice.model.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdentityRepository extends JpaRepository<Identity, String> {
    Optional<Identity> findByIdentity(String id);
}
