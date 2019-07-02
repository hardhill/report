package pfr.centr.report;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;

@Route("")
public class MainView extends Div implements BeforeEnterObserver {

    public MainView() {
        this.setText("MainPage");
    }



    private boolean validEnter(){
        return false;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnter) {
            if(!validEnter()){
                beforeEnter.forwardTo(LoginView.class);
            }
    }
}
