package ISAProject.model;

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
    @Column(name = "b_id")
    private Long b_id;

    @Column(name = "b_name", nullable = false)
    private String b_name;

    @Column(name = "b_surname", nullable = false)
    private String b_surname;

    @Column(name = "b_date_of_birth")
    private Date b_date_of_birth;

    @Column(name = "b_dress_size")
    private Integer b_dress_size;

    @Column(name = "b_shoe_size")
    private Integer b_shoe_size;

    public Long getB_id() {
        return b_id;
    }

    public void setB_id(Long b_id) {
        this.b_id = b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_surname() {
        return b_surname;
    }

    public void setB_surname(String b_surname) {
        this.b_surname = b_surname;
    }

    public Date getB_date_of_birth() {
        return b_date_of_birth;
    }

    public void setB_date_of_birth(Date b_date_of_birth) {
        this.b_date_of_birth = b_date_of_birth;
    }

    public Integer getB_dress_size() {
        return b_dress_size;
    }

    public void setB_dress_size(Integer b_dress_size) {
        this.b_dress_size = b_dress_size;
    }

    public Integer getB_shoe_size() {
        return b_shoe_size;
    }

    public void setB_shoe_size(Integer b_shoe_size) {
        this.b_shoe_size = b_shoe_size;
    }
}
