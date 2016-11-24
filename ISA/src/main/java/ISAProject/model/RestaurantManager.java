package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantmanager")
public class RestaurantManager implements Serializable {

    public RestaurantManager(){

    }

    @Id
    @GeneratedValue
    @Column(name = "rmid")
    private Long rmId;

    @Version
    private int version;

    @Column(name = "rmname", nullable = false)
    private String rmName;

    @Column(name = "rmsurname", nullable = false)
    private String rmSurname;

    @Column(name = "rmpassword", nullable = false)
    private String rmPassword;

    @Column(name = "rmemail", nullable = false)
    private String rmEmail;



    @Enumerated(EnumType.STRING)
    private final User rmtype = User.RESTAURANTMANAGER;

    public Long getRmId() {
        return rmId;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getRmSurname() {
        return rmSurname;
    }

    public void setRmSurname(String rmSurname) {
        this.rmSurname = rmSurname;
    }

    public String getRmPassword() {
        return rmPassword;
    }

    public void setRmPassword(String rmPassword) {
        this.rmPassword = rmPassword;
    }

    public String getRmEmail() {
        return rmEmail;
    }

    public void setRmEmail(String rmEmail) {
        this.rmEmail = rmEmail;
    }
}
