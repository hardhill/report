package pfr.centr.report.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;



public class PasswordPanel extends VerticalLayout {
    Label labelName;
    Label labelPass;
    Label labelWellcome;
    TextField txtName;
    PasswordField txtPass;
    Button bEnter;
    HorizontalLayout topPanel, footerPanel;


    public PasswordPanel() {
        setWidth("325px");
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
        labelName = new Label("Логин:");
        txtName = new TextField();
        lineName.setAlignItems(Alignment.CENTER);
        lineName.add(labelName,txtName);
        labelPass = new Label("Пароль");
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
        bEnter.getStyle().set("cursor","pointer");
        footer.add(bEnter);
        return footer;
    }
}
