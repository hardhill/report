package pfr.centr.report;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;

import javax.servlet.http.HttpServletRequest;

@Route("")
public class MainView extends Div implements BeforeEnterObserver, RouterLayout {

    private HttpServletRequest request;
    public MainView(HttpServletRequest request) {
        this.request = request;
        this.setText("MainPage");
    }



    private boolean validEnter(){
        boolean otvet = false;
        UserLogined userLogined = (UserLogined) request.getSession().getAttribute("user");
        otvet = (userLogined!=null)&&(UserInfo.getInstance().existsUser(userLogined));
        return otvet;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnter) {
            if(!validEnter()){
                beforeEnter.forwardTo(LoginView.class);
            }
    }
}
