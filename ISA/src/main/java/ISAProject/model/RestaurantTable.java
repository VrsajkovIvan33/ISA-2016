package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restauranttable")
public class RestaurantTable implements Serializable {

    public RestaurantTable(){

    }

    @Id
    @GeneratedValue
    @Column(name = "rtaid")
    private Long rtaId;

    @Version
    private int version;

    @Column(name = "rtanumber", nullable = false)
    private int rtaNumber;

    public Long getRtaId() {
        return rtaId;
    }

    public void setRtaId(Long rtaId) {
        this.rtaId = rtaId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRtaNumber() {
        return rtaNumber;
    }

    public void setRtaNumber(int rtaNumber) {
        this.rtaNumber = rtaNumber;
    }
}
