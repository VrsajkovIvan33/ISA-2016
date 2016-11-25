package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "systemmanager")
public class SystemManager implements Serializable {

    public SystemManager(){

    }

    @Id
    @GeneratedValue
    @Column(name = "sid")
    private Long sId;

    @Version
    private int version;

    @Column(name = "sname", nullable = false)
    private String sName;

    @Column(name = "ssurname", nullable = false)
    private String sSurname;

    @Column(name = "spassword", nullable = false)
    private String sPassword;

    @Column(name = "semail", nullable = false)
    private String sEmail;


    @Enumerated(EnumType.STRING)
    private final User stype = User.SYSTEMMANAGER;

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSurname() {
        return sSurname;
    }

    public void setsSurname(String sSurname) {
        this.sSurname = sSurname;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }
}
