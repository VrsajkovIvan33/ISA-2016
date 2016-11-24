package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantsegment")
public class RestaurantSegment implements Serializable {

    public RestaurantSegment(){

    }

    @Id
    @GeneratedValue
    @Column(name = "rsid")
    private Long rsId;

    @Version
    private int version;

    @Column(name = "rsname", nullable = false)
    private String rsName;

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }
}
