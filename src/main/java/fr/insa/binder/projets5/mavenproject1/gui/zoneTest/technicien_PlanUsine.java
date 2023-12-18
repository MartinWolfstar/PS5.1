/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui.zoneTest;


import fr.insa.binder.projets5.mavenproject1.gui.zoneTest.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.map.events.MouseEventDetails;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.hezamu.canvas.Canvas;


@PageTitle("Plannnn")
@Route(value = "3", layout = MainLayout.class)
public class technicien_PlanUsine extends VerticalLayout {

    private Canvas canvas;
    private Button b1;
    private Button b2;
    private Button b3;

    public technicien_PlanUsine() {

        add(new H3("Plan de l'usine"));
        canvas = new Canvas();
        canvas.setWidth("100%");
        canvas.setHeight("300px");
        VerticalLayout buttonRow = createButtons();
        add(buttonRow);
        canvas.fillRect(10, 10, 20, 20);
        drawGrid();

        // Add a mouse move listener to the canvas
        /*canvas.addMouseMoveListener(event -> {
            MouseEventDetails mouseDetails = event.getDetails();
            System.out.println("Mouse moved at " +
                    mouseDetails.getClientX() + "," +
                    mouseDetails.getClientY());
        });*/
    }

    private VerticalLayout createButtons() {
        VerticalLayout buttonRow = new VerticalLayout();
        this.b1 = new Button("Login");
        this.b2 = new Button("Login");
        this.b3 = new Button("Login");
        buttonRow.add(b1,b2,b3);
        this.b1.addClickListener(e -> {
            Notification.show("Hello ");
        });
        this.b2.addClickListener(e -> {
            Notification.show("Hello ");
        });
        this.b3.addClickListener(e -> {
            Notification.show("Hello ");
        });
        return buttonRow;
    }

    private void drawGrid() {
        int gridSize = 20;
        canvas.setStrokeStyle("#000000");
        // Draw vertical grid lines
        for (int x = 0; x <= canvas.getWidth(); x += gridSize) {
            canvas.beginPath();
            canvas.moveTo(x, 0);
            canvas.lineTo(x, canvas.getHeight());
            canvas.stroke();
        }
        // Draw horizontal grid lines
        for (int y = 0; y <= canvas.getHeight(); y += gridSize) {
            canvas.beginPath();
            canvas.moveTo(0, y);
            canvas.lineTo(canvas.getWidth(), y);
            canvas.stroke();
        }
    }
}
