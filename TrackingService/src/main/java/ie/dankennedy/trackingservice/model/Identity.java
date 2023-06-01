package ie.dankennedy.trackingservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "identities")
public class Identity {

    @Id
    private String identity;


    private Float multiplier = 1.0F;

    public Identity() {
        this.identity = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return identity;
    }

    public Float getMultiplier() {
        return multiplier;
    }

    public void increment() {
        this.multiplier += 0.1F;
    }
}
