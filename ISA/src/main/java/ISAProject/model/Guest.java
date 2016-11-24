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
    @Column(name = "gid")
    private Long gId;

    @Version
    private int version;

    @Column(name = "gname")
    private String gName;

    @Column(name = "gsurname")
    private String gSurname;

    @Column(name = "gpassword", nullable = false)
    private String gPassword;

    @Column(name = "gemail", nullable = false)
    private String gEmail;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "gid")
    private List<Guest> friendList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "gid")
    private List<Guest> pendingList = new ArrayList<Guest>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "gid")
    private List<Guest> sentList = new ArrayList<Guest>();

    @Enumerated(EnumType.STRING)
    private final User gtype = User.GUEST;

    public Guest() {
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgSurname() {
        return gSurname;
    }

    public void setgSurname(String gSurname) {
        this.gSurname = gSurname;
    }

    public String getgPassword() {
        return gPassword;
    }

    public void setgPassword(String gPassword) {
        this.gPassword = gPassword;
    }

    public String getgEmail() {
        return gEmail;
    }

    public void setgEmail(String gEmail) {
        this.gEmail = gEmail;
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

    public User getGtype() {
        return gtype;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
