package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "dish")
public class Dish implements Serializable {

    public Dish(){

    }

    @Id
    @GeneratedValue
    @Column(name = "did")
    private Long dId;

    @Version
    private int version;

    @Column(name = "dname", nullable = false)
    private String dName;

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}
