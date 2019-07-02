package pfr.centr.report.models;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
    private static UserInfo ourInstance = new UserInfo();
    private List<UserLogined> users;

    public static UserInfo getInstance() {
        return ourInstance;
    }

    private UserInfo() {

        users = new ArrayList<>();
    }
    /**
     * добавление пользователя в список пользователей залогинившихся к сервису
     */
    public void addUser(UserLogined userLogined) {
        boolean found = false;
        for (UserLogined user : users) {
            found = user.equals(userLogined);
            if (found) break;
        }
        if (!found) users.add(userLogined);
    }

    /**
     * проверяется нахождения записи о пользователе в списке залогинившихся пользователей
     *
     * @param userLogined
     * @return ложь-нет такого пользователя
     */
    public boolean existsUser(UserLogined userLogined) {
        boolean found = false;
        for (UserLogined user : users) {
            found = user.equals(userLogined);
            if (found) {
                break;
            }
        }
        return found;
    }

    /**
     * удаление пользователя из списка залогиненных пользователей
     *
     * @param userLogined
     * @return
     */
    public boolean deleteUser(UserLogined userLogined) {
        return users.remove(userLogined);
    }
}
