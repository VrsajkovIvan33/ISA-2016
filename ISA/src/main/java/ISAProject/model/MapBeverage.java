package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "mapbeverage")
public class MapBeverage implements Serializable {

    public MapBeverage(){

    }

    @Id
    @GeneratedValue
    @Column(name = "mbid")
    private Long mbId;

    @Version
    private int version;

    @Column(name = "mbname", nullable = false)
    private String mbName;

    public Long getMbId() {
        return mbId;
    }

    public void setMbId(Long mbId) {
        this.mbId = mbId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getMbName() {
        return mbName;
    }

    public void setMbName(String mbName) {
        this.mbName = mbName;
    }
}
