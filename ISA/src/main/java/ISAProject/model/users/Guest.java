package ISAProject.model.users;

import ISAProject.model.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Nole on 11/16/2016.
 */
@Entity
@Table(name = "guest")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Guest extends User implements Serializable{

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<Guest> friendList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<Guest> pendingList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    @JsonIgnore
    private List<Guest> sentList = new ArrayList<Guest>();

    @ManyToMany(mappedBy = "acceptedGuests")
    @JsonIgnore
    private Set<Reservation> reservations;

    @Column(name = "active", nullable = false)
    private boolean active;

    public Guest() {
    }

    public Set<Reservation> getReservations() {

        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Guest(User user) {
        super(user);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Guest> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Guest> friendList) {
        this.friendList = friendList;
    }

    public List<Guest> getPendingList() {
        return pendingList;
    }

    public void setPendingList(List<Guest> pendingList) {
        this.pendingList = pendingList;
    }

    public List<Guest> getSentList() {
        return sentList;
    }

    public void setSentList(List<Guest> sentList) {
        this.sentList = sentList;
    }
}
