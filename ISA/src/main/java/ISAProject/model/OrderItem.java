package ISAProject.model;

import ISAProject.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by Verpsychoff on 2/19/2017.
 */

@Entity
@Table(name = "orderitem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderItem {

    public OrderItem() {
        oiStatus = "Waiting";
        oiReadyByArrival = false;
    }

    @Id
    @GeneratedValue
    @Column(name = "oiid")
    private Long id;

    @Version
    private int version;

    //gost koji je narucio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", referencedColumnName = "mid")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oid", referencedColumnName = "oid")
    @JsonIgnore
    private Order order;

    //kuvar ili sanker koji je pripremio naruceno
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffid", referencedColumnName = "id")
    private User staff;

    //"Waiting for waiter", "Waiting", "Currently making", "Ready"
    @Column(name = "oiStatus", nullable = false)
    private String oiStatus;

    //da li treba da bude pripremljeno pre nego sto stigne gost
    @Column(name = "oiReadybyarrival", nullable = false)
    private Boolean oiReadyByArrival;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getOiStatus() {
        return oiStatus;
    }

    public void setOiStatus(String oiStatus) {
        this.oiStatus = oiStatus;
    }

    public Boolean getOiReadyByArrival() {
        return oiReadyByArrival;
    }

    public void setOiReadyByArrival(Boolean oiReadyByArrival) {
        this.oiReadyByArrival = oiReadyByArrival;
    }
}
