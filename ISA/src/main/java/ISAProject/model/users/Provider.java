package ISAProject.model.users;

import ISAProject.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "provider")
public class Provider extends User implements Serializable {

    public Provider(){

    }

    @Column(name = "ppasswordchanged")
    private Boolean pPasswordChanged;

    @ManyToMany(mappedBy = "providers")
    @JsonIgnore
    private List<Restaurant> restaurants;

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Boolean getpPasswordChanged() {
        return pPasswordChanged;
    }

    public void setpPasswordChanged(Boolean pPasswordChanged) {
        this.pPasswordChanged = pPasswordChanged;
    }
}
