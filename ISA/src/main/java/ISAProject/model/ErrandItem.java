package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "erranditem")
public class ErrandItem implements Serializable {

    public ErrandItem(){

    }

    @Id
    @GeneratedValue
    @Column(name = "oiid")
    private Long oiId;

    @Version
    private int version;

    @Column(name = "oiquantity")
    private int oiQuantity;

    public Long getOiId() {
        return oiId;
    }

    public void setOiId(Long oiId) {
        this.oiId = oiId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getOiQuantity() {
        return oiQuantity;
    }

    public void setOiQuantity(int oiQuantity) {
        this.oiQuantity = oiQuantity;
    }
}
