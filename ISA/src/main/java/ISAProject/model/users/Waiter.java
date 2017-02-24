package ISAProject.model.users;

import ISAProject.model.Order;
import ISAProject.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 11/17/2016.
 */
@Entity
@Table(name = "waiter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Waiter extends User implements Serializable {

    public Waiter(){
        orders = new ArrayList<Order>();
    }

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "dress_size")
    private Integer dress_size;

    @Column(name = "shoe_size")
    private Integer shoe_size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant restaurant;

    @Column(name = "review")
    private float review;

    @ManyToMany(mappedBy = "waiters")
    @JsonIgnore
    private List<Order> orders;

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getDress_size() {
        return dress_size;
    }

    public void setDress_size(Integer dress_size) {
        this.dress_size = dress_size;
    }

    public Integer getShoe_size() {
        return shoe_size;
    }

    public void setShoe_size(Integer shoe_size) {
        this.shoe_size = shoe_size;
    }

    public float getReview() {
        return review;
    }

    public void setReview(float review) {
        this.review = review;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
