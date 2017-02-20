package ISAProject.model.users;

import ISAProject.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantmanager")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestaurantManager extends User implements Serializable {

    public RestaurantManager(){
    }

    //nebitno polje, samo da imamo nesto sem id-a za sad
    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant restaurant;

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
