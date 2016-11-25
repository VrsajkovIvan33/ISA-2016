package ISAProject.model.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/17/2016.
 */
@Entity
@Table(name = "waiter")
public class Waiter implements Serializable {

    public Waiter(){
    }

    @Id
    @GeneratedValue
    @Column(name = "wid")
    private Long wId;

    @Version
    private int version;

    @Column(name = "wname", nullable = false)
    private String wName;

    @Column(name = "wsurname", nullable = false)
    private String wSurname;

    @Column(name = "wdate_of_birth")
    private Date wDate_of_birth;

    @Column(name = "wdress_size")
    private Integer wDress_size;

    @Column(name = "wshoe_size")
    private Integer wShoe_size;

    @Column(name = "wpassword", nullable = false)
    private String wPassword;

    @Column(name = "wemail", nullable = false)
    private String wEmail;

    //TODO proveriti za ocenu
    @Column(name = "wreview")
    private float wreview;

    @Enumerated(EnumType.STRING)
    private final User wtype = User.WAITER;

    public Long getwId() {
        return wId;
    }

    public void setwId(Long wId) {
        this.wId = wId;
    }

    public String getwName() {
        return wName;
    }

    public void setwName(String wName) {
        this.wName = wName;
    }

    public String getwSurname() {
        return wSurname;
    }

    public void setwSurname(String wSurname) {
        this.wSurname = wSurname;
    }

    public Date getwDate_of_birth() {
        return wDate_of_birth;
    }

    public void setwDate_of_birth(Date wDate_of_birth) {
        this.wDate_of_birth = wDate_of_birth;
    }

    public Integer getwDress_size() {
        return wDress_size;
    }

    public void setwDress_size(Integer wDress_size) {
        this.wDress_size = wDress_size;
    }

    public Integer getwShoe_size() {
        return wShoe_size;
    }

    public void setwShoe_size(Integer wShoe_size) {
        this.wShoe_size = wShoe_size;
    }

    public String getwPassword() {
        return wPassword;
    }

    public void setwPassword(String wPassword) {
        this.wPassword = wPassword;
    }

    public String getwEmail() {
        return wEmail;
    }

    public void setwEmail(String wEmail) {
        this.wEmail = wEmail;
    }

    public User getWtype() {
        return wtype;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public float getWreview() {
        return wreview;
    }

    public void setWreview(float wreview) {
        this.wreview = wreview;
    }


}
