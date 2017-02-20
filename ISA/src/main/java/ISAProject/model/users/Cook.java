package ISAProject.model.users;

import ISAProject.model.Restaurant;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/17/2016.
 */
@Entity
@Table(name = "cook")
public class Cook extends User implements Serializable {

    public Cook(){
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

    //TODO provera
    /*@Column(name = "ctype", nullable = false)
    @Enumerated(EnumType.STRING)
    private CookType cType;*/
    @Column(name = "typecook", nullable = false)
    private String typeCook;

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

    public String getTypeCook() {
        return typeCook;
    }

    public void setTypeCook(String typeCook) {
        this.typeCook = typeCook;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
