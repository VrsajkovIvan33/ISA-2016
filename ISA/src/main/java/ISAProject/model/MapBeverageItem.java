package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "mapbeverageitem")
public class MapBeverageItem implements Serializable {

    public MapBeverageItem(){

    }

    @Id
    @GeneratedValue
    @Column(name = "mbiid")
    private Long mbiId;

    @Version
    private int version;


    @Column(name = "mbiPrice", nullable = false)
    private float mbiPrice;

    @Column(name = "mbidescription", nullable = false)
    private String mbidescription;

    //TODO proveriti za ocenu
    @Column(name = "mbireview")
    private float mbireview;

    public Long getMbiId() {
        return mbiId;
    }

    public void setMbiId(Long mbiId) {
        this.mbiId = mbiId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getMbiPrice() {
        return mbiPrice;
    }

    public void setMbiPrice(float mbiPrice) {
        this.mbiPrice = mbiPrice;
    }

    public String getMbidescription() {
        return mbidescription;
    }

    public void setMbidescription(String mbidescription) {
        this.mbidescription = mbidescription;
    }

    public float getMbireview() {
        return mbireview;
    }

    public void setMbireview(float mbireview) {
        this.mbireview = mbireview;
    }
}
