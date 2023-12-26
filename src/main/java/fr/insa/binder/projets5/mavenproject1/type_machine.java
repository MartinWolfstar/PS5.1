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
public class type_machine {
    private int id_type_machine;
    private String des_type_machine;

    public type_machine(int id_type_machine, String des_type_machine) {
        this.id_type_machine = id_type_machine;
        this.des_type_machine = des_type_machine;
    }
    public type_machine(String des_type_machine) {
        this(-1,des_type_machine);
    }
    public void save_type_machine(Connection conn) throws SQLException{
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into type_machine_bof (des_type_machine) values (?)")) {
            pst.setString(1, this.des_type_machine);
            pst.executeUpdate();
        }
    }
    
        public static List<type_machine> tousLesTypeMachine(Connection con) throws SQLException {
        List<type_machine> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_type_machine,des_type_machine from type_machine_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_type_machine = rs.getInt("id_type_machine");
                    String des_type_machine = rs.getString("des_type_machine");
                    res.add(new type_machine(id_type_machine, des_type_machine));
                }
            }
        }
        return res;
    }
        
    public static List<String> tousLesTypeMachines_String(Connection con) throws SQLException {
        List<String> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select des_type_machine from type_machine_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String des_type_machine = rs.getString("des_type_machine");
                    res.add(des_type_machine);
                }
            }
        }catch (SQLException ex){
            Notification.show("Problème BdD : type machine" + ex);
        }
        return res;
    }
        
    public static void setDes_type_machine(String des, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update type_machine_bof set des_type_machine = ? where id_type_machine = ?")) {
            pst.setString(1, des);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }catch(SQLException ex){
            Notification.show("Problème BdD : setDes_type_machine");
        }
    }

    public int getId_type_machine() {
        return id_type_machine;
    }
    
    public static int getId_type_machine(String des, Connection con) throws SQLException {
        int id = 0;
        try (PreparedStatement pst = con.prepareStatement(
                "select id_type_machine from type_machine_bof where des_type_machine = ?")) {
            pst.setString(1, des);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id_type_machine");
                }
            }
        }
        return id;
    }
    
    public void supTypeMachine(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from type_machine_bof where id_type_machine = ?")) {
            pst.setInt(1, this.id_type_machine);
            pst.executeUpdate();
            System.out.println("supTypeMachine reussi");
        }
        catch(SQLException ex){
            System.out.println("supTypeMachine non reussi");
        }
    }
    
    public static void supTypeMachine(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from type_machine_bof where id_type_machine = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("supTypeMachine non reussi");
        }
    }

    public String getDes_type_machine() {
        return des_type_machine;
    }
    
    public static String getDes_type_machine(int Id, Connection con) throws SQLException {
        String type_ma = "Erreur";
        try (PreparedStatement pst = con.prepareStatement(
                "select des_type_machine from type_machine_bof where id_type_machine = ?")) {
            pst.setInt(1, Id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    type_ma = rs.getString("des_type_machine");
                }
            }
        }
        catch(SQLException ex){
            Notification.show("Problème BdD : getDes_type_machine");
        }
        return type_ma;
    }

    public void setDes_type_machine(String des_type_machine) {
        this.des_type_machine = des_type_machine;
    }

    @Override
    public String toString() {
        return "type_machine{" + "id_type_machine=" + id_type_machine + ", des_type_machine=" + des_type_machine + '}';
    }
    
}
