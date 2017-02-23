package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Marko on 2/23/2017.
 */
@Entity
@Table(name = "tenderitem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TenderItem implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "tiid")
    private Long tiId;

    @Column(name = "tiname", nullable = false)
    private String tiName;

    @Column(name = "titype", nullable = false)
    private String tiType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    @JsonIgnore
    private Tender tiTender;

    @Version
    private int version;

}
