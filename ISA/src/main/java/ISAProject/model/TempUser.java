package ISAProject.model;

/**
 * Created by Nole on 11/20/2016.
 */
public class TempUser {

    private String email;
    private String password;

    public TempUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public TempUser() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
