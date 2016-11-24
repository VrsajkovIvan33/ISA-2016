package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {

    public MenuItem(){

    }

    @Id
    @GeneratedValue
    @Column(name = "miid")
    private Long miId;

    @Version
    private int version;


    @Column(name = "miPrice", nullable = false)
    private float miPrice;

    @Column(name = "midescription", nullable = false)
    private String midescription;

    //TODO proveriti za ocenu
    @Column(name = "mireview")
    private float mireview;

    public Long getMiId() {
        return miId;
    }

    public void setMiId(Long miId) {
        this.miId = miId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getMiPrice() {
        return miPrice;
    }

    public void setMiPrice(float miPrice) {
        this.miPrice = miPrice;
    }

    public String getMidescription() {
        return midescription;
    }

    public void setMidescription(String midescription) {
        this.midescription = midescription;
    }

    public float getMireview() {
        return mireview;
    }

    public void setMireview(float mireview) {
        this.mireview = mireview;
    }
}
