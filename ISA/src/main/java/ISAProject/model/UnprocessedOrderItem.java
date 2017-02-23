package ISAProject.model;

import ISAProject.model.users.User;

/**
 * Created by Verpsychoff on 2/22/2017.
 */

public class UnprocessedOrderItem {

    private User user;

    private Long orderID;

    private Menu menu;

    private Boolean oiReadyByArrival;

    private String oiStatus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Boolean getOiReadyByArrival() {
        return oiReadyByArrival;
    }

    public void setOiReadyByArrival(Boolean oiReadyByArrival) {
        this.oiReadyByArrival = oiReadyByArrival;
    }

    public String getOiStatus() {
        return oiStatus;
    }

    public void setOiStatus(String oiStatus) {
        this.oiStatus = oiStatus;
    }
}
