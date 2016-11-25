package ISAProject.model.users;

import ISAProject.model.users.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "provider")
public class Provider implements Serializable {

    public Provider(){

    }

    @Id
    @GeneratedValue
    @Column(name = "pid")
    private Long pId;

    @Version
    private int version;

    @Column(name = "pname", nullable = false)
    private String pName;

    @Column(name = "ppassword", nullable = false)
    private String pPassword;

    @Column(name = "pemail", nullable = false)
    private String pEmail;


    @Enumerated(EnumType.STRING)
    private final User ptype = User.PROVIDER;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPassword() {
        return pPassword;
    }

    public void setpPassword(String pPassword) {
        this.pPassword = pPassword;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }
}
