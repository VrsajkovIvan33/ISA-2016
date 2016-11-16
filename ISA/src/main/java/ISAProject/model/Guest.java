package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nole on 11/16/2016.
 */
@Entity
@Table(name = "guest")
public class Guest implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "g_id")
    private Long GId;

    @Column(name = "g_name")
    private String name;

    @Column(name = "g_surname")
    private String surname;

    @Column(name = "g_password", nullable = false)
    private String password;

    @Column(name = "g_email", nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "g_id")
    private List<Guest> friendList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "g_id")
    private List<Guest> pendingList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "g_id")
    private List<Guest> sentList = new ArrayList<Guest>();

    public Guest() {
    }

    public Long getGId() {
        return GId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Guest> getFriendList() {
        return friendList;
    }

    public List<Guest> getPendingList() {
        return pendingList;
    }

    public List<Guest> getSentList() {
        return sentList;
    }

    public void setGId(Long GId) {
        this.GId = GId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFriendList(List<Guest> friendList) {
        this.friendList = friendList;
    }

    public void setPendingList(List<Guest> pendingList) {
        this.pendingList = pendingList;
    }

    public void setSentList(List<Guest> sentList) {
        this.sentList = sentList;
    }
}
