package ISAProject.model;

import ISAProject.model.users.User;

import java.util.Date;

/**
 * Created by Verpychoff on 2/16/2017.
 */

public class UnprocessedCalendarEvent {

    private long id;

    private Date startDate;

    private Date endDate;

    private int dayInWeek;

    private String shiftStart;

    private String shiftEnd;

    private User user;

    private TableRegion tableRegion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDayInWeek() {
        return dayInWeek;
    }

    public void setDayInWeek(int dayInWeek) {
        this.dayInWeek = dayInWeek;
    }

    public String getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(String shiftStart) {
        this.shiftStart = shiftStart;
    }

    public String getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(String shiftEnd) {
        this.shiftEnd = shiftEnd;
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
