package ISAProject.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Marko on 11/16/2016.
 */
@Entity
@Table(name = "kuvar")
public class Kuvar {

    public Kuvar(){

    }

    @Id
    @GeneratedValue
    @Column(name = "ku_id")
    private Long ku_id;

    @Column(name = "ku_ime", nullable = false)
    private String ku_ime;

    @Column(name = "ku_prezime", nullable = false)
    private String ku_prezime;

    @Column(name = "ku_datum_rodjenja")
    private Date ku_datum_rodjenja;

    @Column(name = "ku_konfekcijski_broj")
    private Integer ku_konfekcijski_broj;

    @Column(name = "ku_velicina_obuce")
    private Integer ku_velicina_obuce;

    public Long getKu_id() {
        return ku_id;
    }

    public void setKu_id(Long ku_id) {
        this.ku_id = ku_id;
    }

    public String getKu_ime() {
        return ku_ime;
    }

    public void setKu_ime(String ku_ime) {
        this.ku_ime = ku_ime;
    }

    public String getKu_prezime() {
        return ku_prezime;
    }

    public void setKu_prezime(String ku_prezime) {
        this.ku_prezime = ku_prezime;
    }

    public Date getKu_datum_rodjenja() {
        return ku_datum_rodjenja;
    }

    public void setKu_datum_rodjenja(Date ku_datum_rodjenja) {
        this.ku_datum_rodjenja = ku_datum_rodjenja;
    }

    public Integer getKu_konfekcijski_broj() {
        return ku_konfekcijski_broj;
    }

    public void setKu_konfekcijski_broj(Integer ku_konfekcijski_broj) {
        this.ku_konfekcijski_broj = ku_konfekcijski_broj;
    }

    public Integer getKu_velicina_obuce() {
        return ku_velicina_obuce;
    }

    public void setKu_velicina_obuce(Integer ku_velicina_obuce) {
        this.ku_velicina_obuce = ku_velicina_obuce;
    }
}
