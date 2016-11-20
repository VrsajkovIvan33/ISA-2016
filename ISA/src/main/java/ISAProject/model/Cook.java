package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/17/2016.
 */
@Entity
@Table(name = "cook")
public class Cook implements Serializable {

    public Cook(){
    }

    @Id
    @GeneratedValue
    @Column(name = "cid")
    private Long cId;

    @Column(name = "cname", nullable = false)
    private String cName;

    @Column(name = "csurname", nullable = false)
    private String cSurname;

    @Column(name = "cdate_of_birth")
    private Date cDate_of_birth;

    @Column(name = "cdress_size")
    private Integer cDress_size;

    @Column(name = "cshoe_size")
    private Integer cShoe_size;

    @Column(name = "cpassword", nullable = false)
    private String cPassword;

    @Column(name = "cemail", nullable = false)
    private String cEmail;

    @Enumerated(EnumType.STRING)
    private final User ctype = User.COOK;

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcSurname() {
        return cSurname;
    }

    public void setcSurname(String cSurname) {
        this.cSurname = cSurname;
    }

    public Date getcDate_of_birth() {
        return cDate_of_birth;
    }

    public void setcDate_of_birth(Date cDate_of_birth) {
        this.cDate_of_birth = cDate_of_birth;
    }

    public Integer getcDress_size() {
        return cDress_size;
    }

    public void setcDress_size(Integer cDress_size) {
        this.cDress_size = cDress_size;
    }

    public Integer getcShoe_size() {
        return cShoe_size;
    }

    public void setcShoe_size(Integer cShoe_size) {
        this.cShoe_size = cShoe_size;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public User getCtype() {
        return ctype;
    }
}
