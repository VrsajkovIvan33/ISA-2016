package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marko on 11/16/2016.
 */
@Entity
@Table(name = "konobar")
public class Konobar implements Serializable {

    public Konobar(){

    }

    @Id
    @GeneratedValue
    @Column(name = "ko_id")
    private Long ko_id;

    @Column(name = "ko_ime", nullable = false)
    private String ko_ime;

    @Column(name = "ko_prezime", nullable = false)
    private String ko_prezime;

    @Column(name = "ko_datum_rodjenja")
    private Date ko_datum_rodjenja;

    @Column(name = "ko_konfekcijski_broj")
    private Integer ko_konfekcijski_broj;

    @Column(name = "ko_velicina_obuce")
    private Integer ko_velicina_obuce;

    public Long getKo_id() {
        return ko_id;
    }

    public void setKo_id(Long ko_id) {
        this.ko_id = ko_id;
    }

    public String getKo_ime() {
        return ko_ime;
    }

    public void setKo_ime(String ko_ime) {
        this.ko_ime = ko_ime;
    }

    public String getKo_prezime() {
        return ko_prezime;
    }

    public void setKo_prezime(String ko_prezime) {
        this.ko_prezime = ko_prezime;
    }

    public Date getKo_datum_rodjenja() {
        return ko_datum_rodjenja;
    }

    public void setKo_datum_rodjenja(Date ko_datum_rodjenja) {
        this.ko_datum_rodjenja = ko_datum_rodjenja;
    }

    public Integer getKo_konfekcijski_broj() {
        return ko_konfekcijski_broj;
    }

    public void setKo_konfekcijski_broj(Integer ko_konfekcijski_broj) {
        this.ko_konfekcijski_broj = ko_konfekcijski_broj;
    }

    public Integer getKo_velicina_obuce() {
        return ko_velicina_obuce;
    }

    public void setKo_velicina_obuce(Integer ko_velicina_obuce) {
        this.ko_velicina_obuce = ko_velicina_obuce;
    }
}
