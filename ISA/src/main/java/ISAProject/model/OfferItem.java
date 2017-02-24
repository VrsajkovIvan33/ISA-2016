package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "offeritem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OfferItem implements Serializable {

    public OfferItem(){

    }

    public OfferItem(float offiPrice, String offiDeliveryTime, String offiGuarantee, Offer offiOffer, TenderItem offiTenderItem, int version) {
        this.offiPrice = offiPrice;
        this.offiDeliveryTime = offiDeliveryTime;
        this.offiGuarantee = offiGuarantee;
        this.offiOffer = offiOffer;
        this.offiTenderItem = offiTenderItem;
        this.version = version;
    }

    public OfferItem(OfferItem offerItem){
        this.offiPrice = offerItem.offiPrice;
        this.offiDeliveryTime = offerItem.offiDeliveryTime;
        this.offiGuarantee = offerItem.offiGuarantee;
        this.offiOffer = offerItem.offiOffer;
        this.offiTenderItem = offerItem.offiTenderItem;
        this.version = offerItem.version;
    }

    @Id
    @GeneratedValue
    @Column(name = "offiid")
    private Long offiId;

    @Column(name = "offiprice", nullable = false)
    private float offiPrice;

    @Column(name = "offideliverytime", nullable = false)
    private String offiDeliveryTime;

    @Column(name = "offiguarantee")
    private String offiGuarantee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offid", referencedColumnName = "offid")
    private Offer offiOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tiid", referencedColumnName = "tiid")
    private TenderItem offiTenderItem;

    @Version
    private int version;

    public Long getOffiId() {
        return offiId;
    }

    public void setOffiId(Long offiId) {
        this.offiId = offiId;
    }

    public float getOffiPrice() {
        return offiPrice;
    }

    public void setOffiPrice(float offiPrice) {
        this.offiPrice = offiPrice;
    }

    public String getOffiDeliveryTime() {
        return offiDeliveryTime;
    }

    public void setOffiDeliveryTime(String offiDeliveryTime) {
        this.offiDeliveryTime = offiDeliveryTime;
    }

    public String getOffiGuarantee() {
        return offiGuarantee;
    }

    public void setOffiGuarantee(String offiGuarantee) {
        this.offiGuarantee = offiGuarantee;
    }

    public Offer getOffiOffer() {
        return offiOffer;
    }

    public void setOffiOffer(Offer offiOffer) {
        this.offiOffer = offiOffer;
    }

    public TenderItem getOffiTenderItem() {
        return offiTenderItem;
    }

    public void setOffiTenderItem(TenderItem offiTenderItem) {
        this.offiTenderItem = offiTenderItem;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
