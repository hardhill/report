package pfr.centr.report;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pfr.centr.report.components.PasswordPanel;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;

import javax.servlet.http.HttpServletRequest;


@PageTitle("Report-coster")
@Route("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
    private UserLogined userLogined;
    private HttpServletRequest request;

    public LoginView(HttpServletRequest request){
        userLogined = (UserLogined) request.getSession().getAttribute("user");
        this.request = request;
        PasswordPanel passwordPanel = new PasswordPanel();

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
        if (userLogined != null) {      //есть пользователь
            if (UserInfo.getInstance().existsUser(userLogined)) {
                UserInfo.getInstance().deleteUser(userLogined);
                request.getSession().setAttribute("user", null);
            }
        }
    }
}
