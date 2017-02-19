package ISAProject.model;

import ISAProject.model.users.Waiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Verpsychoff on 2/19/2017.
 */

@Entity
@Table(name = "restaurantorder")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    public Order() {
        //waiters = new ArrayList<Waiter>();
        oStatus = "Waiting";
        oAssigned = false;
    }

    @Id
    @GeneratedValue
    @Column(name = "oid")
    private Long id;

    @Version
    private int version;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rtid", referencedColumnName = "rtid")
    private RestaurantTable restaurantTable;

    //"Waiting", "Currently making" (ne moze da se menja), "Ready", "Served" (moze da se napravi racun)
    @Column(name = "oStatus", nullable = false)
    private String oStatus;

    //slobodna porudzbina ili je dodeljena nekom konobaru
    @Column(name = "oAssigned", nullable =  false)
    private Boolean oAssigned;

    // jer je moguce da se zavrsi smena i drugi krene da ih opsluzuje
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "waiterorders", joinColumns = @JoinColumn(name = "orderid", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "waiterid", referencedColumnName = "id"))
//    private List<Waiter> waiters;

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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public RestaurantTable getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTable restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public String getoStatus() {
        return oStatus;
    }

    public void setoStatus(String oStatus) {
        this.oStatus = oStatus;
    }

    public Boolean getoAssigned() {
        return oAssigned;
    }

    public void setoAssigned(Boolean oAssigned) {
        this.oAssigned = oAssigned;
    }

//    public List<Waiter> getWaiters() {
//        return waiters;
//    }
//
//    public void setWaiters(List<Waiter> waiters) {
//        this.waiters = waiters;
//    }
}
