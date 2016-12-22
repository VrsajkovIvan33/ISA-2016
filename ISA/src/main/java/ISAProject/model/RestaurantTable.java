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
    @Column(name = "rtid")
    private Long rtId;

    @Version
    private int version;

    @Column(name = "rtnumber", nullable = false)
    private int rtNumber;

    public Long getRtId() {
        return rtId;
    }

    public void setRtId(Long rtId) {
        this.rtId = rtId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRtNumber() {
        return rtNumber;
    }

    public void setRtNumber(int rtNumber) {
        this.rtNumber = rtNumber;
    }
}
