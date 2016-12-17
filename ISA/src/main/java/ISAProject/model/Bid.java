package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "bid")
public class Bid implements Serializable {

    public Bid(){

    }

    @Id
    @GeneratedValue
    @Column(name = "bidid")
    private Long bidId;

    @Version
    private int version;

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
