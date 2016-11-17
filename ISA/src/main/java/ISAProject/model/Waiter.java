package ISAProject.model;

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
    @Column(name = "w_id")
    private Long w_id;

    @Column(name = "w_name", nullable = false)
    private String w_name;

    @Column(name = "w_surname", nullable = false)
    private String w_surname;

    @Column(name = "w_date_of_birth")
    private Date w_date_of_birth;

    @Column(name = "w_dress_size")
    private Integer w_dress_size;

    @Column(name = "w_shoe_size")
    private Integer w_shoe_size;

    public Long getW_id() {
        return w_id;
    }

    public void setW_id(Long w_id) {
        this.w_id = w_id;
    }

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public String getW_surname() {
        return w_surname;
    }

    public void setW_surname(String w_surname) {
        this.w_surname = w_surname;
    }

    public Date getW_date_of_birth() {
        return w_date_of_birth;
    }

    public void setW_date_of_birth(Date w_date_of_birth) {
        this.w_date_of_birth = w_date_of_birth;
    }

    public Integer getW_dress_size() {
        return w_dress_size;
    }

    public void setW_dress_size(Integer w_dress_size) {
        this.w_dress_size = w_dress_size;
    }

    public Integer getW_shoe_size() {
        return w_shoe_size;
    }

    public void setW_shoe_size(Integer w_shoe_size) {
        this.w_shoe_size = w_shoe_size;
    }
}
