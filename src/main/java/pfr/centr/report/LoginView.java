package pfr.centr.report;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;

@Route("login")
public class LoginView extends Div {
    public LoginView(HttpServletRequest request){
        System.out.println(request.getSession().getId());
    }
}
