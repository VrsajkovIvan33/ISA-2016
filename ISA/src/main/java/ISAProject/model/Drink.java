package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "drink")
public class Drink implements Serializable {

    public Drink(){

    }

    @Id
    @GeneratedValue
    @Column(name = "drid")
    private Long drId;

    @Version
    private int version;

    @Column(name = "drname", nullable = false)
    private String drName;

    public Long getDrId() {
        return drId;
    }

    public void setDrId(Long drId) {
        this.drId = drId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }
}
