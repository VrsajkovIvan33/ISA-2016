package ISAProject.model;

import ISAProject.model.users.Waiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.operations.Bool;

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
        waiters = new ArrayList<Waiter>();
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

    //"Waiting for waiter", "Waiting", "Currently making" (ne moze da se menja), "Ready" (moze da se napravi racun)
    @Column(name = "oStatus", nullable = false)
    private String oStatus;

    //slobodna porudzbina ili je dodeljena nekom konobaru
    @Column(name = "oAssigned", nullable =  false)
    private Boolean oAssigned;

    // jer je moguce da se zavrsi smena i drugi krene da ih opsluzuje
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "waiterorders", joinColumns = @JoinColumn(name = "orderid", referencedColumnName = "oid"), inverseJoinColumns = @JoinColumn(name = "waiterid", referencedColumnName = "id"))
    private List<Waiter> waiters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wid", referencedColumnName = "id")
    private Waiter currentWaiter;

    @Column(name = "oYear")
    private int year;

    @Column(name = "oMonth")
    private int month;

    @Column(name = "oDay")
    private int day;

    @Column(name = "oHour")
    private int hourOfArrival;

    @Column(name = "oMinute")
    private int minuteOfArrival;

    @Column(name = "oBillCreated")
    private Boolean billCreated;

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

    public List<Waiter> getWaiters() {
        return waiters;
    }

    public void setWaiters(List<Waiter> waiters) {
        this.waiters = waiters;
    }

    public Waiter getCurrentWaiter() {
        return currentWaiter;
    }

    public void setCurrentWaiter(Waiter currentWaiter) {
        this.currentWaiter = currentWaiter;
    }

    public int getHourOfArrival() {
        return hourOfArrival;
    }

    public void setHourOfArrival(int hourOfArrival) {
        this.hourOfArrival = hourOfArrival;
    }

    public int getMinuteOfArrival() {
        return minuteOfArrival;
    }

    public void setMinuteOfArrival(int minuteOfArrival) {
        this.minuteOfArrival = minuteOfArrival;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Boolean getBillCreated() {
        return billCreated;
    }

    public void setBillCreated(Boolean billCreated) {
        this.billCreated = billCreated;
    }
}
