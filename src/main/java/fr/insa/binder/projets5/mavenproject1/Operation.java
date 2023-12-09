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
public class Operation {
    
    private int id_o;
    private int id_p;
    private int id_to;
    
    public Operation(int id_o, int id_to, int id_p) {
        this.id_o = id_o;
        this.id_p = id_p;
        this.id_to = id_to;
    }
    
    public Operation(int id_to, int id_p) {
        this(-1, id_to, id_p);
    }
    
    public static Operation demande() {
        int id_p = ConsoleFdB.entreeInt("id_p : ");
        int id_to = ConsoleFdB.entreeInt("id_to : ");
        return new Operation(id_to, id_p);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into macchhiinnee (id_p,id_to) values (?,?)")) {
            pst.setInt(1, this.id_p);
            pst.setInt(2, this.id_to);
            pst.executeUpdate();
        }
    } 
    
    public void supOperation(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from macchhiinnee where id_o = ?")) {
            pst.setInt(1, this.id_o);
            pst.executeUpdate();
        }
    }
    
    public static void supOperation(Connection con, int id_o) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from macchhiinnee where id_o = ?")) {
            pst.setInt(1, id_o);
            pst.executeUpdate();
        }
    }
    
    public static List<Operation> tousLesOperations(Connection con) throws SQLException {
        List<Operation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_o,id_to,id_p from macchhiinnee")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_o = rs.getInt("id_o");
                    int id_to = rs.getInt("id_to");
                    int id_p = rs.getInt("id_p");
                    res.add(new Operation(id_o, id_to, id_p));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Operation" + "id_o=" + getId() + ", id_p=" + getRef() + ", id_to=" + getDes() + '}';
    }

    public void setRef(int id_p) {
        this.id_p = id_p;
    }

    public static void setRef(int id_p, int id_o, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update macchhiinnee set id_p = ? where id_o = ?")) {
            pst.setInt(1, id_p);
            pst.setInt(2, id_o);            
            pst.executeUpdate();
        }
    }
    
    public void setDes(int id_to) {
        this.id_to = id_to;
    }

    public static void setDes(int id_to, int id_o, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update macchhiinnee set id_to = ? where id_o = ?")) {
            pst.setInt(1, id_to);
            pst.setInt(2, id_o);            
            pst.executeUpdate();
        }
    }
    
    public void setId(int id_o) {
        this.id_o = id_o;
    }

    public int getRef() {
        return id_p;
    }

    public int getDes() {
        return id_to;
    }

    public int getId() {
        return id_o;
    }
}

