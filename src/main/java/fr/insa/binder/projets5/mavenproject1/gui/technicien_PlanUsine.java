/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1.gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.hezamu.canvas.Canvas;

@PageTitle("Plan de l'usine")
@Route(value = "", layout = MainLayout.class)
public class technicien_PlanUsine extends VerticalLayout {

    private Canvas canvas;

    public technicien_PlanUsine() {
        // Title
        add(new H3("Plan de l'usine"));

        // Drawing area (Canvas)
        canvas = new Canvas();
        canvas.setWidth("100%");
        canvas.setHeight("300px"); // Adjust the height as needed

        // Numbered row of buttons
        VerticalLayout buttonRow = createButtonRow();

        // Add components to the main layout
        add(canvas, buttonRow);

        // Draw a filled rectangle on the canvas
        canvas.fillRect(10, 10, 20, 20);

        // Draw grid on the canvas
        drawGrid();

        // Add a mouse move listener to the canvas
        canvas.addMouseMoveListener(event -> {
            MouseEventDetails mouseDetails = event.getDetails();
            System.out.println("Mouse moved at " +
                    mouseDetails.getClientX() + "," +
                    mouseDetails.getClientY());
        });
    }

    private VerticalLayout createButtonRow() {
        VerticalLayout buttonRow = new VerticalLayout();

        // Add numbered buttons to the row
        for (int i = 1; i <= 5; i++) {
            buttonRow.add(new Button(String.valueOf(i)));
        }

        return buttonRow;
    }

    private void drawGrid() {
        int gridSize = 20; // Adjust the grid size as needed

        // Set the stroke style for the grid lines
        canvas.setStrokeStyle("#000000"); // Black color

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
