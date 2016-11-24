package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantreview")
public class RestaurantReview implements Serializable {

    public RestaurantReview(){

    }

    @Id
    @GeneratedValue
    @Column(name = "rrid")
    private Long rrId;

    @Version
    private int version;

    @Column(name = "rrNumber", nullable = false)
    private float rrNumber;

    public Long getRrId() {
        return rrId;
    }

    public void setRrId(Long rrId) {
        this.rrId = rrId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getRrNumber() {
        return rrNumber;
    }

    public void setRrNumber(float rrNumber) {
        this.rrNumber = rrNumber;
    }
}
