package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

    public Restaurant(){
        restaurantTableArrangements = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    @Column(name = "rid")
    private Long id;

    @Version
    private int version;

    @Column(name = "rname", nullable = false)
    private String rName;

    @Column(name = "rtype", nullable = false)
    private String rType;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantTableArrangement> restaurantTableArrangements;

    //TODO proveriti za ocenu
    /*@Column(name = "rreview")
    private float rreview;*/

    //TODO proveriti
    /*@OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "mid")
    private Menu mmenu;*/

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

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }

    public List<RestaurantTableArrangement> getRestaurantTableArrangements() {
        return restaurantTableArrangements;
    }

    public void setRestaurantTableArrangements(List<RestaurantTableArrangement> restaurantTableArrangements) {
        this.restaurantTableArrangements = restaurantTableArrangements;
    }
}
