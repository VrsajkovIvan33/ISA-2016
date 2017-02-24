package ISAProject.model;

import ISAProject.model.users.Guest;
import ISAProject.model.users.Waiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Verpsychoff on 2/24/2017.
 */

@Entity
@Table(name = "visithistory")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VisitHistory {

    public VisitHistory() {
        orderItems = new ArrayList<OrderItem>();
        restaurantGrade = -1;
        menuGrade = -1;
        serviceGrade = -1;
    }

    @Id
    @GeneratedValue
    @Column(name = "vhid")
    private Long id;

    @Version
    private int version;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orderItemsInHistory", joinColumns = @JoinColumn(name = "vhid", referencedColumnName = "vhid"), inverseJoinColumns = @JoinColumn(name = "oiid", referencedColumnName = "oiid"))
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wid", referencedColumnName = "id")
    private Waiter waiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gid", referencedColumnName = "id")
    private Guest guest;

    @Column(name = "vhRestaurantGrade")
    private float restaurantGrade;

    @Column(name = "vhServiceGrade")
    private float serviceGrade;

    @Column(name = "vhMenuGrade")
    private float menuGrade;

    @Column(name = "vhDate")
    private Date date;

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

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public float getRestaurantGrade() {
        return restaurantGrade;
    }

    public void setRestaurantGrade(float restaurantGrade) {
        this.restaurantGrade = restaurantGrade;
    }

    public float getServiceGrade() {
        return serviceGrade;
    }

    public void setServiceGrade(float serviceGrade) {
        this.serviceGrade = serviceGrade;
    }

    public float getMenuGrade() {
        return menuGrade;
    }

    public void setMenuGrade(float menuGrade) {
        this.menuGrade = menuGrade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
