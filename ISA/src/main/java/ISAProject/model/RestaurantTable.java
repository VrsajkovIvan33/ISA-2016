package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restauranttable")
public class RestaurantTable implements Serializable {

    public RestaurantTable(){
    }

    @Id
    @GeneratedValue
    @Column(name = "rtid")
    private Long id;

    @Version
    private int version;

    @Column(name = "rtNumber", nullable = false)
    private int rtNumber;

    @Column(name = "rtPosition", nullable = false)
    private int rtPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rsid", referencedColumnName = "rsid")
    private RestaurantSegment restaurantSegment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trid", referencedColumnName = "trid")
    private TableRegion tableRegion;

    @Column(name = "rtActive", nullable = false)
    private Boolean rtActive;

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

    public int getRtNumber() {
        return rtNumber;
    }

    public void setRtNumber(int rtNumber) {
        this.rtNumber = rtNumber;
    }

    public int getRtPosition() { return rtPosition; }

    public void setRtPosition(int rtPosition) { this.rtPosition = rtPosition; }

    public Boolean getRtActive() { return rtActive; }

    public void setRtActive(Boolean rtActive) { this.rtActive = rtActive; }

    public RestaurantSegment getRestaurantSegment() {
        return restaurantSegment;
    }

    public void setRestaurantSegment(RestaurantSegment restaurantSegment) {
        this.restaurantSegment = restaurantSegment;
    }

    public TableRegion getTableRegion() {
        return tableRegion;
    }

    public void setTableRegion(TableRegion tableRegion) {
        this.tableRegion = tableRegion;
    }
}
