package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    public Menu(){

    }

    @Id
    @GeneratedValue
    @Column(name = "mid")
    private Long mId;

    @Version
    private int version;

    @Column(name = "mname", nullable = false)
    private String mName;


    //TODO proveriti
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    private Set<MenuItem> dishes;*/

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
