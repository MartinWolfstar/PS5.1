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
 * @author abinder01
 */
public class machine {
    private int id;
    private int ref;
    private String des;
    private int id_poste_de_travail;
    private int id_type_machine;

    private machine(int id, int ref, String des, int id_poste_de_travail, int id_type_machine) {
        this.id = id;
        this.ref = ref;
        this.des = des;
        this.id_poste_de_travail = id_poste_de_travail;
        this.id_type_machine = id_type_machine;
    }

    
    public machine(int ref, String des, int id_poste_de_travail, int id_type_machine) {
       this(-1, ref, des, id_poste_de_travail, id_type_machine);
    }
    public machine(int id, String des, int ref) {
        this.id = id;
        this.ref = ref;
        this.des = des;
    }
    
    public machine(String des, int ref) {
        this(-1, des, ref);
    }
    
    public static machine demande() {
        int ref = ConsoleFdB.entreeInt("ref : ");
        String des = ConsoleFdB.entreeString("des : ");
        return new machine(des, ref);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into machine_bof (ref_machine,des_machine,id_poste_de_travail,id_type_machine) values (?,?,?,?)")) {
            pst.setInt(1, this.ref);
            pst.setString(2, this.des);
            pst.setInt(3, this.id_poste_de_travail);
            pst.setInt(4, this.id_type_machine);
            pst.executeUpdate();
        }
    } 
    
    public void supMachine(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from machine_bof where id_machine = ?")) {
            pst.setInt(1, this.id);
            pst.executeUpdate();
        }
    }
    
    public static void supMachine(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from machine_bof where id_machine = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<machine> tousLesMachines(Connection con) throws SQLException {
        List<machine> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_machine,des_machine,ref_machine from machine_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_machine");
                    String des = rs.getString("des_machine");
                    int ref = rs.getInt("ref_machine");
                    res.add(new machine(id, des, ref));
                }
            }
        }
        return res;
    }
    
        public static List<String> tousLesMachines_String(Connection con) throws SQLException {
        List<String> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select des_machine from machine_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    String des_machine = rs.getString("des_machine");
                    res.add(des_machine);
                }
            }
        }catch (SQLException ex){
            Notification.show("Problème BdD : machine" + ex);
        }
        return res;
    }

    @Override
    public String toString() {
        return "machine{" + "id=" + id + ", ref=" + ref + ", des=" + des + ", id_poste_de_travail=" + id_poste_de_travail + ", id_type_machine=" + id_type_machine + '}';
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public static void setRef(int ref, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update machine_bof set ref_machine = ? where id_machine = ?")) {
            pst.setInt(1, ref);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public void setDes(String des) {
        this.des = des;
    }

    public static void setDes(String des, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update machine_bof set des_machine = ? where id_machine = ?")) {
            pst.setString(1, des);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
    }
    
    public static String getDes_machine(int Id, Connection con) throws SQLException {
        String type_ma = "Erreur";
        try (PreparedStatement pst = con.prepareStatement(
                "select des_machine from machine_bof where id_machine = ?")) {
            pst.setInt(1, Id);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    type_ma = rs.getString("des_machine");
                }
            }
        }
        catch(SQLException ex){
            Notification.show("Problème BdD : getDes_machine");
        }
        return type_ma;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getRef() {
        return ref;
    }

    public String getDes() {
        return des;
    }

    public int getId() {
        return id;
    }
    
        public static int getId_machine(String des, Connection con) throws SQLException {
        int id = 0;
        try (PreparedStatement pst = con.prepareStatement(
                "select id_machine from machine_bof where des_machine = ?")) {
            pst.setString(1, des);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id_machine");
                }
            }
        }
        return id;
    }

    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }

    public int getId_type_machine() {
        return id_type_machine;
    }
    
}
