package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "tender")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tender implements Serializable {

    public Tender(){

    }

    public Tender(Date tStart, Date tEnd, String tStatus, Restaurant tRestaurant, int version) {
        this.tStart = tStart;
        this.tEnd = tEnd;
        this.tStatus = tStatus;
        this.tRestaurant = tRestaurant;
        this.version = version;
    }

    public Tender(Tender tender){
        this.tStart = tender.tStart;
        this.tEnd = tender.tEnd;
        this.tStatus = tender.tStatus;
        this.tRestaurant = tender.tRestaurant;
        this.version = tender.version;
    }

    @Id
    @GeneratedValue
    @Column(name = "tid")
    private Long tId;

    @Column(name = "tstart", nullable = false)
    private Date tStart;

    @Column(name = "tend", nullable = false)
    private Date tEnd;

    @Column(name = "tstatus", nullable = false)
    private String tStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant tRestaurant;

    @Version
    private int version;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Date gettStart() {
        return tStart;
    }

    public void settStart(Date tStart) {
        this.tStart = tStart;
    }

    public Date gettEnd() {
        return tEnd;
    }

    public void settEnd(Date tEnd) {
        this.tEnd = tEnd;
    }

    public String gettStatus() {
        return tStatus;
    }

    public void settStatus(String tStatus) {
        this.tStatus = tStatus;
    }

    public Restaurant gettRestaurant() {
        return tRestaurant;
    }

    public void settRestaurant(Restaurant tRestaurant) {
        this.tRestaurant = tRestaurant;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
