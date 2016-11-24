package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

    public Restaurant(){

    }

    @Id
    @GeneratedValue
    @Column(name = "rid")
    private Long rId;

    @Version
    private int version;

    @Column(name = "rname", nullable = false)
    private String rName;

    @Column(name = "rtype", nullable = false)
    private String rType;


    //TODO proveriti za ocenu
    /*@Column(name = "rreview")
    private float rreview;*/

    //TODO proveriti
    /*@OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "mid")
    private Menu mmenu;*/

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrType() {
        return rType;
    }

    public void setrType(String rType) {
        this.rType = rType;
    }
}
