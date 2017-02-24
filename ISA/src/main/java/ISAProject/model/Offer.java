package ISAProject.model;

import ISAProject.model.users.Provider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "offer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Offer implements Serializable {

    public Offer(){

    }

    public Offer(String offStatus, Provider offProvider, Tender offTender, int version) {
        this.offStatus = offStatus;
        this.offProvider = offProvider;
        this.offTender = offTender;
        this.version = version;
    }

    public Offer(Offer offer){
        this.offStatus = offer.offStatus;
        this.offProvider = offer.offProvider;
        this.offTender = offer.offTender;
        this.version = offer.version;
    }

    @Id
    @GeneratedValue
    @Column(name = "offid")
    private Long offId;

    @Column(name = "offstatus", nullable = false)
    private String offStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", referencedColumnName = "id")
    private Provider offProvider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    private Tender offTender;

    @Version
    private int version;

    public Long getOffId() {
        return offId;
    }

    public void setOffId(Long offId) {
        this.offId = offId;
    }

    public String getOffStatus() {
        return offStatus;
    }

    public void setOffStatus(String offStatus) {
        this.offStatus = offStatus;
    }

    public Provider getOffProvider() {
        return offProvider;
    }

    public void setOffProvider(Provider offProvider) {
        this.offProvider = offProvider;
    }

    public Tender getOffTender() {
        return offTender;
    }

    public void setOffTender(Tender offTender) {
        this.offTender = offTender;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
