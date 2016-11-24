package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "bill")
public class Bill implements Serializable {

    public Bill(){

    }

    @Id
    @GeneratedValue
    @Column(name = "billid")
    private Long billId;

    @Version
    private int version;

    @Column(name = "billdate")
    private Date billDate;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
