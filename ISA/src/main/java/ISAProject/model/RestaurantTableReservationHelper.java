package ISAProject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Nole on 2/23/2017.
 */
public class RestaurantTableReservationHelper {

    private RestaurantTable table;
    private boolean reserved;
    private boolean selected;

    public RestaurantTableReservationHelper() {
    }

    public RestaurantTableReservationHelper(RestaurantTable table, boolean reserved, boolean selected) {
        this.table = table;
        this.reserved = reserved;
        this.selected = selected;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
