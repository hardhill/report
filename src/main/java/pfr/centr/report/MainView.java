package pfr.centr.report;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import pfr.centr.report.models.UserInfo;
import pfr.centr.report.models.UserLogined;

@Route("")
public class MainView extends Div implements BeforeEnterObserver, RouterLayout {
//@formatter:off
    String style = ".bar-style{" +
        "border-bottom: 1px solid #868D93;" +
        "background-color: #EAECEC;"+
        "};";
//@formatter:on

    public MainView() {
        add(BuildTopMenu());
    }


    private Component BuildTopMenu(){
        HorizontalLayout menuBlock = new HorizontalLayout();
        menuBlock.setPadding(false); menuBlock.setSpacing(false);
        menuBlock.setMargin(false);
        menuBlock.setWidthFull();
        menuBlock.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        addClassName("bar-style");
        menuBlock.add(new Button("LOGO"),new Button("X"));
        return menuBlock;
    }
    private boolean validEnter(){
        boolean otvet = false;
        UserLogined userLogined = (UserLogined) UI.getCurrent().getSession().getAttribute("user");
        UserInfo userinfo = UserInfo.getInstance();
        otvet = (userLogined!=null)&&(userinfo.existsUser(userLogined));
        return otvet;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnter) {
            if(!validEnter()){
                beforeEnter.forwardTo(LoginView.class);
            }
    }
}
