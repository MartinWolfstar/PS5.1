package fr.insa.binder.projets5.mavenproject1.gui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("main")
@Route(value = "", layout = MainLayout.class)
public class MainView extends VerticalLayout {

    private TextField name;
    private Button sayHello;
    private AppLayoutDrawer ALD;

    public MainView() {
        
        name = new TextField("Your neeme");
        sayHello = new Button("Say hello");
        //ALD = new AppLayoutDrawer();

        
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        //setHorizontalComponentAlignment(FlexComponent.Alignment.END, name, sayHello);

        add(name, sayHello);
    }

}

