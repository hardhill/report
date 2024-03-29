package pfr.centr.report.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;


public class PasswordPanel extends VerticalLayout {
    Caption labelName;
    Caption labelPass;
    Label labelWellcome;
    TextField txtName;
    PasswordField txtPass;
    Button bEnter;
    HorizontalLayout topPanel, footerPanel;

    private List listeners = new ArrayList();

    public PasswordPanel() {
        setWidth("340px");
        add(CreateCaption(),CreateForm(), CreateFooter());
    }

    private Component CreateCaption(){
        topPanel = new HorizontalLayout();
        labelWellcome = new Label("Добро пожаловать!");
        topPanel.setWidth("100%");
        topPanel.setAlignItems(Alignment.CENTER);
        topPanel.setJustifyContentMode(JustifyContentMode.CENTER);
        topPanel.setMargin(false);
        topPanel.getStyle().set("background-color","#bce3fe");
        topPanel.add(labelWellcome);
        return  topPanel;
    }

    private Component CreateForm(){
        HorizontalLayout lineName = new HorizontalLayout();
        HorizontalLayout linePass = new HorizontalLayout();
        labelName = new Caption("Логин:","75px",JustifyContentMode.END);
        txtName = new TextField();
        lineName.setAlignItems(Alignment.CENTER);
        lineName.add(labelName,txtName);
        labelPass = new Caption("Пароль:","75px",JustifyContentMode.END);
        txtPass = new PasswordField();
        linePass.setAlignItems(Alignment.CENTER);
        linePass.add(labelPass,txtPass);
        VerticalLayout panelForm = new VerticalLayout();
        panelForm.getStyle().set("background-color","#ebf7ff");
        panelForm.getStyle().set("margin","0px");
        panelForm.add(lineName, linePass);
        return panelForm;
    }

    private Component CreateFooter(){
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(false);
        footer.setSpacing(false);
        footer.setPadding(false);
        footer.setWidthFull();
        footer.setJustifyContentMode(JustifyContentMode.CENTER);
        footer.getStyle().set("background-color","#c3ced3");
        bEnter = new Button("Вход");
        bEnter.addClickShortcut(Key.ENTER);
        bEnter.addClickListener(e->{
            String login = txtName.getValue();
            String pass = txtPass.getValue();
            fireClickOnPanel(login, pass);
        });
        bEnter.getStyle().set("cursor","pointer");
        footer.add(bEnter);
        return footer;
    }

    public void setLoginText(String txt){
        if(txtName!=null) txtName.setValue(txt);
    }

    public void  setPasswordText(String pass){
        if(txtPass!=null) txtPass.setValue(pass);
    }

    public void addPassOkListener(IPassOkListener listener){
            this.listeners.add(listener);
    }

    protected void fireClickOnPanel(String login, String pass){
        for(int i=0; i<listeners.size();i++){
            IPassOkListener listener = (IPassOkListener)listeners.get(i);
            listener.OnClickOkButton(login,pass);
        }
    }

    public void deletePassOkListener(IPassOkListener listener){
        listeners.remove(listener);
    }

    public List<IPassOkListener> getOkPassListeners(){
        return listeners;
    }
    public interface IPassOkListener {
        public void OnClickOkButton(String login, String pass);
    }
}
