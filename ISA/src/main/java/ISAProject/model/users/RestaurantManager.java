package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantmanager")
public class RestaurantManager extends User implements Serializable {

    public RestaurantManager(){

    }

    //nebitno polje, samo da imamo nesto sem id-a za sad
    @Column(name = "date_of_birth")
    private Date date_of_birth;

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

}
