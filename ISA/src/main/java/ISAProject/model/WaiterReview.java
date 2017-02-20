package ISAProject.model;

import ISAProject.model.users.User;
import ISAProject.model.users.Waiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 2/19/2017.
 */
@Entity
@Table(name = "waiterreview")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaiterReview implements Serializable {

    public WaiterReview(){

    }

    public WaiterReview(int version, float wrReview, Date wrDate, Waiter wrWaiter, Restaurant wrRestaurant) {
        this.version = version;
        this.wrReview = wrReview;
        this.wrDate = wrDate;
        this.wrWaiter = wrWaiter;
        this.wrRestaurant = wrRestaurant;
    }

    public WaiterReview(WaiterReview waiterReview){
        this.version = waiterReview.version;
        this.wrReview = waiterReview.wrReview;
        this.wrDate = waiterReview.wrDate;
        this.wrWaiter = waiterReview.wrWaiter;
        this.wrRestaurant = waiterReview.wrRestaurant;
    }

    @Id
    @GeneratedValue
    @Column(name = "wrid")
    private Long wrId;

    @Version
    private int version;

    @Column(name = "wrreview", nullable = false)
    private float wrReview;

    @Column(name = "wrdate", nullable = false)
    private Date wrDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wid", referencedColumnName = "id")
    private Waiter wrWaiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant wrRestaurant;

    public Long getWrId() {
        return wrId;
    }

    public void setWrId(Long wrId) {
        this.wrId = wrId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getWrReview() {
        return wrReview;
    }

    public void setWrReview(float wrReview) {
        this.wrReview = wrReview;
    }

    public Date getWrDate() {
        return wrDate;
    }

    public void setWrDate(Date wrDate) {
        this.wrDate = wrDate;
    }

    public Waiter getWrWaiter() {
        return wrWaiter;
    }

    public void setWrWaiter(Waiter wrWaiter) {
        this.wrWaiter = wrWaiter;
    }

    public Restaurant getWrRestaurant() {
        return wrRestaurant;
    }

    public void setWrRestaurant(Restaurant wrRestaurant) {
        this.wrRestaurant = wrRestaurant;
    }
}
