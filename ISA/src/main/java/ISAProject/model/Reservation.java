package ISAProject.model;

import ISAProject.model.users.Guest;
import ISAProject.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nole on 2/23/2017.
 */

@Entity
@Table(name = "reservation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Version
    private int version;

    @Column(name = "date")
    private Date date;

    @Column(name = "timeH")
    private int timeH;

    @Column(name = "timeM")
    private int timeM;

    @Column(name = "durationH")
    private int durationH;

    @Column(name = "durationM")
    private int durationM;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "oid", referencedColumnName = "oid")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hostId", referencedColumnName = "id")
    private Guest host;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservationpendingguests", joinColumns = @JoinColumn(name = "reservationid", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "guestid", referencedColumnName = "id", nullable = false))
    private Set<Guest> pendingGuests;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "reservationacceptedguests", joinColumns = @JoinColumn(name = "reservationid", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "guestid", referencedColumnName = "id", nullable = false))
    private Set<Guest> acceptedGuests;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "reservationtable", joinColumns = @JoinColumn(name = "reservationId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tableId", referencedColumnName = "rtid"))
    private List<RestaurantTable> tables;

    public Reservation() {
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeH() {
        return timeH;
    }

    public void setTimeH(int timeH) {
        this.timeH = timeH;
    }

    public int getTimeM() {
        return timeM;
    }

    public void setTimeM(int timeM) {
        this.timeM = timeM;
    }

    public int getDurationH() {
        return durationH;
    }

    public void setDurationH(int durationH) {
        this.durationH = durationH;
    }

    public int getDurationM() {
        return durationM;
    }

    public void setDurationM(int durationM) {
        this.durationM = durationM;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Guest getHost() {
        return host;
    }

    public void setHost(Guest host) {
        this.host = host;
    }

    public Set<Guest> getPendingGuests() {
        return pendingGuests;
    }

    public void setPendingGuests(Set<Guest> pendingGuests) {
        this.pendingGuests = pendingGuests;
    }

    public Set<Guest> getAcceptedGuests() {
        return acceptedGuests;
    }

    public void setAcceptedGuests(Set<Guest> acceptedGuests) {
        this.acceptedGuests = acceptedGuests;
    }
}
