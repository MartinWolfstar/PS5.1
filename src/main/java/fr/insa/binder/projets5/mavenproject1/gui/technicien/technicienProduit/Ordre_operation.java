/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package fr.insa.binder.projets5.mavenproject1.gui.technicien.technicienProduit;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.combobox.ComboBox;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.server.VaadinSession;
//import static fr.insa.binder.projets5.mavenproject1.Operation.setTypeOperation;
//import fr.insa.binder.projets5.mavenproject1.type_operation;
//import static fr.insa.binder.projets5.mavenproject1.type_operation.getId_type_operation;
//import static fr.insa.binder.projets5.mavenproject1.type_operation.tousLesTypeOperations_String;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
///**Salut
// *
// * @author binde
// */
//public class Ordre_operation extends HorizontalLayout{
//    
//    private ComboBox combo;
//    private Button bouton;
//    
//    public Ordre_operation(List<Integer> liste){
//        ComboBox<String> combo = new ComboBox<>();
//            try {
//                combo.setItems(liste);
//            } catch (SQLException ex) {
//                Notification.show("Problème BdD : a");
//            }
//
//            combo.addValueChangeListener(event -> {
//                String selectedValue = event.getValue();
//                try {
//                    int id_type_op = getId_type_operation(selectedValue, (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                    Operation.setId_typeOperation(id_type_op);
//                    setTypeOperation(Operation.getId_typeOperation(), Operation.getId_operation(), (Connection) VaadinSession.getCurrent().getAttribute("conn"));
//                    //UI.getCurrent().getPage().reload();
//                    this.getDataProvider().refreshItem(Operation);
////                    this.setItems(Operation.tousLesOperations_produit((Connection) VaadinSession.getCurrent().getAttribute("conn"), id_produit));
//                } catch (SQLException ex) {
//                    Notification.show("Problème BdD : m2");
//                }
//            });
//    }
//    
//}
