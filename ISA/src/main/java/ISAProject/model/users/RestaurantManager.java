package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantmanager")
public class RestaurantManager extends User implements Serializable {

    public RestaurantManager(){

    }

}
