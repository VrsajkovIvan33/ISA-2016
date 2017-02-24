package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "offer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Offer implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "offid")
    private Long offId;

    /*@Column(name = "tstart", nullable = false)
    private Date tStart;

    @Column(name = "tend", nullable = false)
    private Date tEnd;

    @Column(name = "tstatus", nullable = false)
    private String tStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    private Restaurant tRestaurant;

    @OneToMany(mappedBy = "tender", cascade = CascadeType.ALL)
    private List<TenderItem> tenderItems;*/

    @Version
    private int version;
}
