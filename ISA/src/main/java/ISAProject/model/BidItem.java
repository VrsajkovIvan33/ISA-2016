package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "biditem")
public class BidItem implements Serializable {

    public BidItem(){

    }

    @Id
    @GeneratedValue
    @Column(name = "bidiid")
    private Long bidiId;

    @Version
    private int version;

    @Column(name = "bidiprice", nullable = false)
    private float bidiPrice;

    @Column(name = "bidideliveryTime")
    private int bidiDeliveryTime;

    @Column(name = "bidiguarantee")
    private String bidiGuarantee;

    public Long getBidiId() {
        return bidiId;
    }

    public void setBidiId(Long bidiId) {
        this.bidiId = bidiId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getBidiPrice() {
        return bidiPrice;
    }

    public void setBidiPrice(float bidiPrice) {
        this.bidiPrice = bidiPrice;
    }

    public int getBidiDeliveryTime() {
        return bidiDeliveryTime;
    }

    public void setBidiDeliveryTime(int bidiDeliveryTime) {
        this.bidiDeliveryTime = bidiDeliveryTime;
    }

    public String getBidiGuarantee() {
        return bidiGuarantee;
    }

    public void setBidiGuarantee(String bidiGuarantee) {
        this.bidiGuarantee = bidiGuarantee;
    }
}
