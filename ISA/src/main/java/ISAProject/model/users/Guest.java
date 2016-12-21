package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nole on 11/16/2016.
 */
@Entity
@Table(name = "guest")
public class Guest extends User implements Serializable{

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id")
    private List<Guest> friendList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id")
    private List<Guest> pendingList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id")
    private List<Guest> sentList = new ArrayList<Guest>();

    @Column(name = "active", nullable = false)
    private boolean active;

    public Guest() {
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
