package pfr.centr.report.models;

import java.util.Objects;

public class UserLogined {
    private String login;
    private String ipadress;
    private String sessionid;

    public UserLogined(String login, String ipadress, String sessionid) {
        this.login = login;
        this.ipadress = ipadress;
        this.sessionid = sessionid;
    }

    public UserLogined() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getIpadress() {
        return ipadress;
    }

    public void setIpadress(String ipadress) {
        this.ipadress = ipadress;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString() {
        return "UserLogined{" +
                "login='" + login + '\'' +
                ", ipadress='" + ipadress + '\'' +
                ", sessionid='" + sessionid + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLogined)) return false;
        UserLogined that = (UserLogined) o;
        return Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getIpadress(), that.getIpadress()) &&
                Objects.equals(getSessionid(), that.getSessionid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getIpadress(), getSessionid());
    }
}
