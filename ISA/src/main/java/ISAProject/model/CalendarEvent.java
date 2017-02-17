package ISAProject.model;

import ISAProject.model.users.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Verpychoff on 2/16/2017.
 */

@Entity
@Table(name = "calendarevent")
public class CalendarEvent {

    @Id
    @GeneratedValue
    @Column(name = "ceid")
    private long id;

    @Version
    private int version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ceStart")
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ceEnd")
    private Date end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trid", referencedColumnName = "trid")
    private TableRegion tableRegion;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TableRegion getTableRegion() {
        return tableRegion;
    }

    public void setTableRegion(TableRegion tableRegion) {
        this.tableRegion = tableRegion;
    }
}
