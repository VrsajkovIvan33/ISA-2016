package ISAProject.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Marko on 11/16/2016.
 */
@Entity
@Table(name = "sanker")
public class Sanker {

    public Sanker(){

    }

    @Id
    @GeneratedValue
    @Column(name = "sa_id")
    private Long sa_id;

    @Column(name = "sa_ime", nullable = false)
    private String sa_ime;

    @Column(name = "sa_prezime", nullable = false)
    private String sa_prezime;

    @Column(name = "sa_datum_rodjenja")
    private Date sa_datum_rodjenja;

    @Column(name = "sa_konfekcijski_broj")
    private Integer sa_konfekcijski_broj;

    @Column(name = "sa_velicina_obuce")
    private Integer sa_velicina_obuce;

    public Long getSa_id() {
        return sa_id;
    }

    public void setSa_id(Long sa_id) {
        this.sa_id = sa_id;
    }

    public String getSa_ime() {
        return sa_ime;
    }

    public void setSa_ime(String sa_ime) {
        this.sa_ime = sa_ime;
    }

    public String getSa_prezime() {
        return sa_prezime;
    }

    public void setSa_prezime(String sa_prezime) {
        this.sa_prezime = sa_prezime;
    }

    public Date getSa_datum_rodjenja() {
        return sa_datum_rodjenja;
    }

    public void setSa_datum_rodjenja(Date sa_datum_rodjenja) {
        this.sa_datum_rodjenja = sa_datum_rodjenja;
    }

    public Integer getSa_konfekcijski_broj() {
        return sa_konfekcijski_broj;
    }

    public void setSa_konfekcijski_broj(Integer sa_konfekcijski_broj) {
        this.sa_konfekcijski_broj = sa_konfekcijski_broj;
    }

    public Integer getSa_velicina_obuce() {
        return sa_velicina_obuce;
    }

    public void setSa_velicina_obuce(Integer sa_velicina_obuce) {
        this.sa_velicina_obuce = sa_velicina_obuce;
    }
}
