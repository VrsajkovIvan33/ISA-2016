package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 11/24/2016.
 */
@Entity
@Table(name = "restaurantsegment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RestaurantSegment implements Serializable {

    public RestaurantSegment(){

    }

    @Id
    @GeneratedValue
    @Column(name = "rsid")
    private Long rsId;

    @Version
    private int version;

    @Column(name = "rsName", nullable = false)
    private String rsName;

    @Column(name = "rsColor", nullable = false)
    private String rsColor;

    public Long getRsId() {
        return rsId;
    }

    public void setRsId(Long rsId) {
        this.rsId = rsId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public String getRsColor() {
        return rsColor;
    }

    public void setRsColor(String rsColor) {
        this.rsColor = rsColor;
    }
}
