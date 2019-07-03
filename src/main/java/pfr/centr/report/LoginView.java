package pfr.centr.report;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import pfr.centr.report.components.PasswordPanel;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;




@PageTitle("Report-coster")
@Route("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    private UserLogined userLogined;
    private PasswordPanel passwordPanel;


    public LoginView(){
        userLogined = (UserLogined) UI.getCurrent().getSession().getAttribute("user");

        passwordPanel = new PasswordPanel();
        passwordPanel.addPassOkListener(new PasswordPanel.IPassOkListener() {
            @Override
            public void OnClickOkButton(String login, String pass) {
                if(login.equals("admin")&&pass.equals("admin")){
                    String sId = VaadinSession.getCurrent().getSession().getId();
                    String sIP = UI.getCurrent().getSession().getBrowser().getAddress();
                    UserLogined userLogined = new UserLogined();
                    userLogined.setIpadress(sIP); userLogined.setSessionid(sId); userLogined.setLogin(login);
                    UI.getCurrent().getSession().setAttribute("user",userLogined);
                    UserInfo.getInstance().addUser(userLogined);
                    UI.getCurrent().navigate(MainView.class);
                }else{
                    Notification notification = new Notification("А пароль-то не подходит! Пробуем снова.",3000, Notification.Position.MIDDLE);
                    notification.open();
                    passwordPanel.setLoginText(""); passwordPanel.setPasswordText("");

                }
            }
        });
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
        setMargin(false);
        setPadding(false);
        setSpacing(false);
        add(passwordPanel);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnter) {
        passwordPanel.setPasswordText("");
        if (userLogined != null) {      //есть пользователь
            if (UserInfo.getInstance().existsUser(userLogined)) {
                UserInfo.getInstance().deleteUser(userLogined);
                UI.getCurrent().getSession().setAttribute("user", null);
                UI.getCurrent().getSession().close();
            }
        }
    }
}
