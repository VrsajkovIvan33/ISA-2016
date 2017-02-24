package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "offeritem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OfferItem implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "offiid")
    private Long offiId;
}
