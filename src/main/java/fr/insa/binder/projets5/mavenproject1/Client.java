/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

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
public class Client {
    
    /*private int id;
    private String nom_client;
    private String mdp;
    private int id_com;
    
    public Client (int id, String nom_client, String mdp) {
        this.id = id;
        this.nom_client = nom_client;
        this.mdp = mdp;
        this.id_com = id_com;
    }
    
    public Client (String nom_client, String mdp) {
        this(-1, nom_client, mdp);
    }
    
    public static Client demande() {
        String nom_client = ConsoleFdB.entreeString("Nom : ");
        String mdp = ConsoleFdB.entreeString("mdp : ");
        return new Client(nom_client, mdp);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into macchhiinnee (ref,des) values (?,?)")) {
            pst.setInt(1, this.ref);
            pst.setString(2, this.des);
            pst.executeUpdate();
        }
    } 
    
    public void supMachine(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from macchhiinnee where id = ?")) {
            pst.setInt(1, this.id);
            pst.executeUpdate();
        }
    }
    
    public static void supMachine(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from macchhiinnee where id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<machine> tousLesMachines(Connection con) throws SQLException {
        List<machine> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id,des,ref from macchhiinnee")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String des = rs.getString("des");
                    int ref = rs.getInt("ref");
                    res.add(new machine(id, des, ref));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Machine{" + "id=" + getId() + ", ref=" + getRef() + ", des=" + getDes() + '}';
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public static void setRef(int ref, int id, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update macchhiinnee set ref = ? where id = ?")) {
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
                "update macchhiinnee set des = ? where id = ?")) {
            pst.setString(1, des);
            pst.setInt(2, id);            
            pst.executeUpdate();
        }
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
    }*/
    
}
