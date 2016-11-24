package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {

    public Order(){

    }

    @Id
    @GeneratedValue
    @Column(name = "oid")
    private Long oId;

    @Version
    private int version;

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
