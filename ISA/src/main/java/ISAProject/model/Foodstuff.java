package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "foodstuff")
public class Foodstuff implements Serializable {

    public Foodstuff(){

    }

    @Id
    @GeneratedValue
    @Column(name = "fid")
    private Long fId;

    @Version
    private int version;

    @Column(name = "fname", nullable = false)
    private String fName;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
