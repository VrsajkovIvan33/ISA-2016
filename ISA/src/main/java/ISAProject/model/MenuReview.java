package ISAProject.model;

import ISAProject.model.users.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 2/19/2017.
 */
@Entity
@Table(name = "menureview")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MenuReview implements Serializable {

    public MenuReview(){

    }

    public MenuReview(int version, float mrReview, Date mrDate, User mrUser, Restaurant mrRestaurant, Menu mrMenu) {
        this.version = version;
        this.mrReview = mrReview;
        this.mrDate = mrDate;
        this.mrUser = mrUser;
        this.mrRestaurant = mrRestaurant;
        this.mrMenu = mrMenu;
    }

    public MenuReview(MenuReview menuReview){
        this.version = menuReview.version;
        this.mrReview = menuReview.mrReview;
        this.mrDate = menuReview.mrDate;
        this.mrUser = menuReview.mrUser;
        this.mrRestaurant = menuReview.mrRestaurant;
        this.mrMenu = menuReview.mrMenu;
    }

    @Id
    @GeneratedValue
    @Column(name = "mrid")
    private Long mrId;

    @Version
    private int version;

    @Column(name = "mrreview", nullable = false)
    private float mrReview;

    @Column(name = "mrdate", nullable = false)
    private Date mrDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    private User mrUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant mrRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid", referencedColumnName = "mid")
    private Menu mrMenu;

    public Long getMrId() {
        return mrId;
    }

    public void setMrId(Long mrId) {
        this.mrId = mrId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getMrReview() {
        return mrReview;
    }

    public void setMrReview(float mrReview) {
        this.mrReview = mrReview;
    }

    public Date getMrDate() {
        return mrDate;
    }

    public void setMrDate(Date mrDate) {
        this.mrDate = mrDate;
    }

    public User getMrUser() {
        return mrUser;
    }

    public void setMrUser(User mrUser) {
        this.mrUser = mrUser;
    }

    public Restaurant getMrRestaurant() {
        return mrRestaurant;
    }

    public void setMrRestaurant(Restaurant mrRestaurant) {
        this.mrRestaurant = mrRestaurant;
    }

    public Menu getMrMenu() {
        return mrMenu;
    }

    public void setMrMenu(Menu mrMenu) {
        this.mrMenu = mrMenu;
    }
}
