package ISAProject.model;

import ISAProject.model.users.Waiter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "bill")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bill implements Serializable {

    public Bill(){

    }

    @Id
    @GeneratedValue
    @Column(name = "blid")
    private Long id;

    @Version
    private int version;

    @Column(name = "blDate")
    private Date date;

    @Column(name = "blTotal")
    private float total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wid", referencedColumnName = "id")
    private Waiter waiter;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }
}
