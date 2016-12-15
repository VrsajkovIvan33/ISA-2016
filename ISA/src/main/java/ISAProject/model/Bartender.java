package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/17/2016.
 */
@Entity
@Table(name = "bartender")
public class Bartender implements Serializable {

    public Bartender(){
    }

    @Column(name = "date_of_birth")
    private Date date_of_birth;

    @Column(name = "dress_size")
    private Integer dress_size;

    @Column(name = "shoe_size")
    private Integer shoe_size;

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
}
