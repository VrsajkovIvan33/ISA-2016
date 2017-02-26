package ISAProject.model;

import java.util.Date;

/**
 * Created by Nole on 2/23/2017.
 */
public class ReservationHelper {

    private Date date;
    private int timeH;
    private int timeM;
    private int durationH;
    private int durationM;

    public ReservationHelper() {
    }

    public ReservationHelper(Date date, int timeH, int timeM, int durationH, int durationM) {
        this.date = date;
        this.timeH = timeH;
        this.timeM = timeM;
        this.durationH = durationH;
        this.durationM = durationM;
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
}
