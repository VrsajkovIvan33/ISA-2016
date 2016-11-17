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
    @Column(name = "c_id")
    private Long c_id;

    @Column(name = "c_name", nullable = false)
    private String c_name;

    @Column(name = "c_surname", nullable = false)
    private String c_surname;

    @Column(name = "c_date_of_birth")
    private Date c_date_of_birth;

    @Column(name = "c_dress_size")
    private Integer c_dress_size;

    @Column(name = "c_shoe_size")
    private Integer c_shoe_size;

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_surname() {
        return c_surname;
    }

    public void setC_surname(String c_surname) {
        this.c_surname = c_surname;
    }

    public Date getC_date_of_birth() {
        return c_date_of_birth;
    }

    public void setC_date_of_birth(Date c_date_of_birth) {
        this.c_date_of_birth = c_date_of_birth;
    }

    public Integer getC_dress_size() {
        return c_dress_size;
    }

    public void setC_dress_size(Integer c_dress_size) {
        this.c_dress_size = c_dress_size;
    }

    public Integer getC_shoe_size() {
        return c_shoe_size;
    }

    public void setC_shoe_size(Integer c_shoe_size) {
        this.c_shoe_size = c_shoe_size;
    }
}
