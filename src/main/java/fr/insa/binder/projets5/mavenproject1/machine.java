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
public class machine {
    
    private int ref;
    private String des;
    private int id;
    
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

    public void setDes(String des) {
        this.des = des;
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
}
