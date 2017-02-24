package ISAProject.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Nole on 2/23/2017.
 */

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Version
    private int version;

    @Column(name = "date")
    private Date date;

    @Column(name = "timeH")
    private int timeH;

    @Column(name = "timeM")
    private int timeM;

    @Column(name = "durationH")
    private int durationH;

    @Column(name = "durationM")
    private int durationM;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "reservationtable", joinColumns = @JoinColumn(name = "reservationId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tableId", referencedColumnName = "rtid"))
    private List<RestaurantTable> tables;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeH() {
        return timeH;
    }

    public void setTimeH(int timeH) {
        this.timeH = timeH;
    }

    public int getTimeM() {
        return timeM;
    }

    public void setTimeM(int timeM) {
        this.timeM = timeM;
    }

    public int getDurationH() {
        return durationH;
    }

    public void setDurationH(int durationH) {
        this.durationH = durationH;
    }

    public int getDurationM() {
        return durationM;
    }

    public void setDurationM(int durationM) {
        this.durationM = durationM;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }
}
