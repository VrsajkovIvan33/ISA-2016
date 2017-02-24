package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Menu implements Serializable {

    public Menu(){
    }

    public Menu(String mName, String mType, String mDescription, float mPrice, float mReview, Restaurant mRestaurant, int version) {
        this.mName = mName;
        this.mType = mType;
        this.mDescription = mDescription;
        this.mPrice = mPrice;
        this.mReview = mReview;
        this.mRestaurant = mRestaurant;
        this.version = version;
    }

    public Menu(Menu menu){
        this.mName = menu.mName;
        this.mType = menu.mType;
        this.mDescription = menu.mDescription;
        this.mPrice = menu.mPrice;
        this.mReview = menu.mReview;
        this.mRestaurant = menu.mRestaurant;
        this.version = menu.version;
    }

    @Id
    @GeneratedValue
    @Column(name = "mid")
    private Long mId;

    @Column(name = "mname", nullable = false)
    private String mName;

    @Column(name = "mtype", nullable = false)
    private String mType;

    @Column(name = "mdescription")
    private String mDescription;

    @Column(name = "mprice")
    private float mPrice;

    @Column(name = "mreview")
    private float mReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant mRestaurant;

    @Version
    private int version;

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public float getmPrice() {
        return mPrice;
    }

    public void setmPrice(float mPrice) {
        this.mPrice = mPrice;
    }

    public float getmReview() {
        return mReview;
    }

    public void setmReview(float mReview) {
        this.mReview = mReview;
    }

    public Restaurant getmRestaurant() {
        return mRestaurant;
    }

    public void setmRestaurant(Restaurant mRestaurant) {
        this.mRestaurant = mRestaurant;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
