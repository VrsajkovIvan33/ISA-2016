package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "provider")
public class Provider extends User implements Serializable {

    public Provider(){

    }
}
