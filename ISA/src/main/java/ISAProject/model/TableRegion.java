package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Verpsychoff on 2/16/2017.
 */

@Entity
@Table(name = "tableregion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TableRegion implements Serializable {

    public TableRegion(){
    }

    @Id
    @GeneratedValue
    @Column(name = "trid")
    private Long id;

    @Version
    private int version;

    @Column(name = "trMark", nullable = false)
    private int trMark;

    @Column(name = "trColor", nullable = false)
    private String trColor;

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

    public int getTrMark() {
        return trMark;
    }

    public void setTrMark(int trMark) {
        this.trMark = trMark;
    }

    public String getTrColor() {
        return trColor;
    }

    public void setTrColor(String trColor) {
        this.trColor = trColor;
    }
}
