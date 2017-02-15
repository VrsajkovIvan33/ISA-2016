package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Verpsychoff on 12/21/2016.
 */
@Entity
@Table(name = "restauranttablearrangement")
public class RestaurantTableArrangement implements Serializable {

    public RestaurantTableArrangement() {
    }

    @Id
    @GeneratedValue
    @Column(name = "rtaid")
    private Long id;

    @Version
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant restaurant;

    @Column(name = "rtanumber")
    private int rtaNumber;

    @Column(name = "rtaposition")
    private int rtaPosition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getRtaNumber() {
        return rtaNumber;
    }

    public void setRtaNumber(int rtaNumber) {
        this.rtaNumber = rtaNumber;
    }

    public int getRtaPosition() {
        return rtaPosition;
    }

    public void setRtaPosition(int rtaPosition) {
        this.rtaPosition = rtaPosition;
    }
}
