/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import com.vaadin.flow.component.notification.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author melan
 */
public class poste_de_travail {
   private int id_poste_de_travail;
   private String ref_poste_de_travail; 
   private int x;
   private int y;

    public poste_de_travail(int id_poste_de_travail, String ref_poste_de_travail) {
        this.id_poste_de_travail = id_poste_de_travail;
        this.ref_poste_de_travail = ref_poste_de_travail;
    }
    public poste_de_travail(int id_poste_de_travail, String ref_poste_de_travail, int x, int y) {
        this.id_poste_de_travail = id_poste_de_travail;
        this.ref_poste_de_travail = ref_poste_de_travail;
        this.x = x;
        this.y = y;
    }
    public poste_de_travail(String ref_poste_de_travail) {
        this(-1, ref_poste_de_travail);
    }
    public poste_de_travail(String ref_poste_de_travail, int x, int y) {
        this(-1, ref_poste_de_travail,x,y);
    }
    
    
    public void save_poste_de_travail(Connection conn) throws SQLException{
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into poste_de_travail_bof (ref_poste_de_travail) values (?)",
            PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.ref_poste_de_travail);
            pst.executeUpdate();
            try (ResultSet ids = pst.getGeneratedKeys()) {
                if (ids.next()) {
                    this.id_poste_de_travail = ids.getInt(1);
                }
            }
        } 
    }
    
    public static List<poste_de_travail> tousLesPostes(Connection con) throws SQLException {
        List<poste_de_travail> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_poste_de_travail,ref_poste_de_travail from poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_poste_de_travail = rs.getInt("id_poste_de_travail");
                    String ref_poste_de_travail = rs.getString("ref_poste_de_travail");
                    res.add(new poste_de_travail(id_poste_de_travail, ref_poste_de_travail));
                }
            }
        }
        return res;
    }
    public static List<String> tousLesPostes_String(Connection con) throws SQLException {
        List<String> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select ref_poste_de_travail from poste_de_travail_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String ref_poste_de_travail = rs.getString("ref_poste_de_travail");
                    res.add(ref_poste_de_travail);
                }
            }
        }catch (SQLException ex){
            Notification.show("Problème BdD : poste_de_travail" + ex);
        }
        return res;
    }
    
    
    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }
    public static int getId_poste_de_travail(String ref, Connection con) throws SQLException {
        int id = 0;
        try (PreparedStatement pst = con.prepareStatement(
                "select id_poste_de_travail from poste_de_travail_bof where ref_poste_de_travail = ?")) {
            pst.setString(1, ref);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id_poste_de_travail");
                }
            }
        }
        return id;
    }

    public String getRef_poste_de_travail() {
        return ref_poste_de_travail;
    }
    
    public static String getRef_poste_de_travail(int Id, Connection con) throws SQLException {
        String pt = "Erreur1";
        try (PreparedStatement pst = con.prepareStatement(
                "select ref_poste_de_travail from poste_de_travail_bof where id_poste_de_travail = ?")) {
            pst.setInt(1, Id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    pt = rs.getString("ref_poste_de_travail");
                }
            }
            catch(SQLException ex){
                Notification.show("Problème BdD : getRef_poste_de_travail 1");
            }
        }
        catch(SQLException ex){
            Notification.show("Problème BdD : getRef_poste_de_travail");
        }
        return pt;
    }

    public void setRef_poste_de_travail(String ref_poste_de_travail) {
        this.ref_poste_de_travail = ref_poste_de_travail;
    }

    public void setId_poste_de_travail(int id_poste_de_travail) {
        this.id_poste_de_travail = id_poste_de_travail;
    }

    @Override
    public String toString() {
        return "poste_de_travail{" + "id_poste_de_travail=" + id_poste_de_travail + ", ref_poste_de_travail=" + ref_poste_de_travail + '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
   
}
