package ie.dankennedy.trackingservice.model;

import ie.dankennedy.trackingservice.repository.IdentityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentityService {

        private final IdentityRepository identityRepository;
        @Autowired
        public IdentityService(IdentityRepository identityRepository) {
            this.identityRepository = identityRepository;
        }

        public Optional<Identity> getIdentity(String id) {
            return identityRepository.findByIdentity(id);
        }
        @Transactional
        public void incrementIdentity(String id) {

            System.out.println("In IdentityService: Incrementing identity: " + id);

            Optional<Identity> identity = getIdentity(id);

            if (identity.isEmpty()) {
                throw new RuntimeException("Identity not found");
            }
            Identity identity1 = identity.get();
            identity1.increment();
            this.identityRepository.save(identity1);
        }
        @Transactional
        public Identity buildIdentity() {
            Identity identity = new Identity();
            System.out.println("In IdentityService: Building new identity: " + identity);
            this.identityRepository.save(identity);
            System.out.println("In IdentityService: Built new identity: " + identityRepository.findByIdentity(identity.getId()));
            return identity;
        }
}
