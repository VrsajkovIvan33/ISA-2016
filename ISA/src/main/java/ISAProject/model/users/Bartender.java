package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/17/2016.
 */
@Entity
@Table(name = "bartender")
public class Bartender implements Serializable {

    public Bartender(){
    }

    @Id
    @GeneratedValue
    @Column(name = "bid")
    private Long bId;

    @Version
    private int version;

    @Column(name = "bname", nullable = false)
    private String bName;

    @Column(name = "bsurname", nullable = false)
    private String bSurname;

    @Column(name = "bdate_of_birth")
    private Date bDate_of_birth;

    @Column(name = "bdress_size")
    private Integer bDress_size;

    @Column(name = "bshoe_size")
    private Integer bShoe_size;

    @Column(name = "bpassword", nullable = false)
    private String bPassword;

    @Column(name = "bemail", nullable = false)
    private String bEmail;

    @Enumerated(EnumType.STRING)
    private final User btype = User.BARTENDER;

    public Long getbId() {
        return bId;
    }

    public void setbId(Long bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbSurname() {
        return bSurname;
    }

    public void setbSurname(String bSurname) {
        this.bSurname = bSurname;
    }

    public Date getbDate_of_birth() {
        return bDate_of_birth;
    }

    public void setbDate_of_birth(Date bDate_of_birth) {
        this.bDate_of_birth = bDate_of_birth;
    }

    public Integer getbDress_size() {
        return bDress_size;
    }

    public void setbDress_size(Integer bDress_size) {
        this.bDress_size = bDress_size;
    }

    public Integer getbShoe_size() {
        return bShoe_size;
    }

    public void setbShoe_size(Integer bShoe_size) {
        this.bShoe_size = bShoe_size;
    }

    public String getbPassword() {
        return bPassword;
    }

    public void setbPassword(String bPassword) {
        this.bPassword = bPassword;
    }

    public String getbEmail() {
        return bEmail;
    }

    public void setbEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    public User getBtype() {
        return btype;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


}
