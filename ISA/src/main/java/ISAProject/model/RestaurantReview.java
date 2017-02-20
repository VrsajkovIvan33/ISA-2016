package ISAProject.model;

import ISAProject.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantreview")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestaurantReview implements Serializable {

    public RestaurantReview(){

    }

    public RestaurantReview(int version, float rrReview, Date rrDate, User rrUser, Restaurant rrRestaurant) {
        this.version = version;
        this.rrReview = rrReview;
        this.rrDate = rrDate;
        this.rrUser = rrUser;
        this.rrRestaurant = rrRestaurant;
    }

    public RestaurantReview(RestaurantReview restaurantReview){
        this.version = restaurantReview.version;
        this.rrReview = restaurantReview.rrReview;
        this.rrDate = restaurantReview.rrDate;
        this.rrUser = restaurantReview.rrUser;
        this.rrRestaurant = restaurantReview.rrRestaurant;
    }

    @Id
    @GeneratedValue
    @Column(name = "rrid")
    private Long rrId;

    @Version
    private int version;

    @Column(name = "rrreview", nullable = false)
    private float rrReview;

    @Column(name = "rrdate", nullable = false)
    private Date rrDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    private User rrUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant rrRestaurant;

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

    public float getRrReview() {
        return rrReview;
    }

    public void setRrReview(float rrReview) {
        this.rrReview = rrReview;
    }

    public Date getRrDate() {
        return rrDate;
    }

    public void setRrDate(Date rrDate) {
        this.rrDate = rrDate;
    }

    public User getRrUser() {
        return rrUser;
    }

    public void setRrUser(User rrUser) {
        this.rrUser = rrUser;
    }

    public Restaurant getRrRestaurant() {
        return rrRestaurant;
    }

    public void setRrRestaurant(Restaurant rrRestaurant) {
        this.rrRestaurant = rrRestaurant;
    }
}
