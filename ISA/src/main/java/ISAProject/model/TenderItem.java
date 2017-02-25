package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "tenderitem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TenderItem implements Serializable {

    public TenderItem(){

    }

    public TenderItem(String tiName, String tiType, String tiQuantity, Tender tiTender, int version) {
        this.tiName = tiName;
        this.tiType = tiType;
        this.tiQuantity = tiQuantity;
        this.tiTender = tiTender;
        this.version = version;
    }

    public TenderItem(TenderItem tenderItem){
        this.tiName = tenderItem.tiName;
        this.tiType = tenderItem.tiType;
        this.tiQuantity = tenderItem.tiQuantity;
        this.tiTender = tenderItem.tiTender;
        this.version = tenderItem.version;
    }

    @Id
    @GeneratedValue
    @Column(name = "tiid")
    private Long tiId;

    @Column(name = "tiname", nullable = false)
    private String tiName;

    @Column(name = "titype", nullable = false)
    private String tiType;

    @Column(name = "tiquantity")
    private String tiQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    private Tender tiTender;

    @Version
    private int version;


    public Long getTiId() {
        return tiId;
    }

    public void setTiId(Long tiId) {
        this.tiId = tiId;
    }

    public String getTiName() {
        return tiName;
    }

    public void setTiName(String tiName) {
        this.tiName = tiName;
    }

    public String getTiType() {
        return tiType;
    }

    public void setTiType(String tiType) {
        this.tiType = tiType;
    }

    public String getTiQuantity() {
        return tiQuantity;
    }

    public void setTiQuantity(String tiQuantity) {
        this.tiQuantity = tiQuantity;
    }

    public Tender getTiTender() {
        return tiTender;
    }

    public void setTiTender(Tender tiTender) {
        this.tiTender = tiTender;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
