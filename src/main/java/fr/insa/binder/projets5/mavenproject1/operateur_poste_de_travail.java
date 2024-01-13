/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class operateur_poste_de_travail {
    private int id_operateur;
    private int id_poste_de_travail;

    public operateur_poste_de_travail(int id_operateur, int id_poste_de_travail) {
        this.id_operateur = id_operateur;
        this.id_poste_de_travail = id_poste_de_travail;
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into operations__poste_de_travail_bof (id_operateur,id_poste_de_travail) values (?,?)")) {
            pst.setInt(1, this.id_operateur);
            pst.setInt(2, this.id_poste_de_travail);
            pst.executeUpdate();
        }catch(SQLException ex){
            Notification.show("Problème BdD : save opdt : " + ex);
        }
    }
    
    public void supOpe_Poste(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations__poste_de_travail_bof where id_operateur = ?")) {
            pst.setInt(1, this.id_operateur);
            pst.executeUpdate();
        }
    }
    
    public static void supOpe_Poste(Connection con, int id_poste_de_travail) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations__poste_de_travail_bof where id_poste_de_travail = ?")) {
            pst.setInt(1, id_poste_de_travail);
            pst.executeUpdate();
        }
    }
    
    public static List<operateur_poste_de_travail> tousLesOpe_Poste(Connection con) throws SQLException {
        List<operateur_poste_de_travail> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operateur,id_poste_de_travail from operations__poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operateur = rs.getInt("id_operateur");
                    int id_poste_de_travail = rs.getInt("id_poste_de_travail");
                    res.add(new operateur_poste_de_travail(id_operateur, id_poste_de_travail));
                }
            }catch(SQLException ex){
                Notification.show("Problème BdD : op_poste 1" + ex);
            }
        }catch(SQLException ex){
            Notification.show("Problème BdD : op_poste 2" + ex);
        }
        return res;
    }
    
    public static List<operateur_poste_de_travail> tousLesOperateursByPosteDeTravail(int id_poste_de_travail, Connection con) throws SQLException {
        List<operateur_poste_de_travail> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "SELECT id_operateur, id_poste_de_travail FROM operations__poste_de_travail_bof WHERE id_poste_de_travail = ?")) {
            pst.setInt(1, id_poste_de_travail);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operateur = rs.getInt("id_operateur");
                    res.add(new operateur_poste_de_travail(id_operateur, id_poste_de_travail));
                }
            } catch (SQLException ex) {
                Notification.show("Problème BdD : op_poste 1" + ex.getMessage());
            }
        } catch (SQLException ex) {
            Notification.show("Problème BdD : op_poste 2" + ex.getMessage());
        }
        return res;
    }

    @Override
    public String toString() {
        return "operations__poste_de_travail_bof{" + "id_operateur=" + id_operateur + ", id_poste_de_travail=" + id_poste_de_travail + '}';
    }
    
    public int getId_operateur() {
        return id_operateur;
    }
    public String getNom_operateur() {
        String nom;
        try {
            nom = operateur.getnom_operateur(id_operateur,(Connection) VaadinSession.getCurrent().getAttribute("conn"));
        } catch (SQLException ex) {
            nom = "erreur BDD, id non identifié";
        }
        return nom;
    }
    

    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }

    public void setId_operateur(int id_operateur) {
        this.id_operateur = id_operateur;
    }

    public void setId_poste_de_travail(int id_poste_de_travail) {
        this.id_poste_de_travail = id_poste_de_travail;
    }

}
