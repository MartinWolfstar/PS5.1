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
 * @author binde
 */
public class typeoperation {
    
    private int id_to;   
    private String des_to;

    
    public typeoperation(int id_to, String des_to) {
        this.id_to = id_to;
        this.des_to = des_to;
    }
    
    public typeoperation(String des_to) {
        this(-1, des_to);
    }
    
    public static typeoperation demande() {
        String des = ConsoleFdB.entreeString("des : ");
        return new typeoperation(des);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into typeoperation (des) values (?)")) {
            pst.setString(1, this.des_to);
            pst.executeUpdate();
        }
    } 
    
    public void supMachine(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from typeoperation where id = ?")) {
            pst.setInt(1, this.id_to);
            pst.executeUpdate();
        }
    }
    
    public static void supMachine(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from typeoperation where id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<typeoperation> tousLesTO(Connection con) throws SQLException {
        List<typeoperation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id,des from typeoperation")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String des = rs.getString("des");
                    res.add(new typeoperation(id, des));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Type operation" + "id=" + getId() + "des=" + getDes() + '}';
    }

    
    public void setDes(String des) {
        this.des_to = des;
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
        this.id_to = id;
    }

    public String getDes() {
        return des_to;
    }

    public int getId() {
        return id_to;
    }
}
    

