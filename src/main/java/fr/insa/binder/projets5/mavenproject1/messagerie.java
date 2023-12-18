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
public class messagerie {
    private int id;
    private String mes;
    private int id_op;

    private messagerie(int id, String mes, int id_op) {
        this.id = id;
        this.mes = mes;
        this.id_op = id_op;
    }

    
    public messagerie(String mes, int id_op) {
       this(-1, mes, id_op);
    }
    
    public static messagerie demande() {
        int id_op = ConsoleFdB.entreeInt("id_op : ");
        String mes = ConsoleFdB.entreeString("mes : ");
        return new messagerie(mes, id_op);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into messagerie_bof (id_operateur,message) values (?,?)")) {
            //pst.setInt(1, this.id);
            pst.setInt(1, this.id_op);
            pst.setString(2, this.mes);
            pst.executeUpdate();
        }
    } 
    
    public void supMessage(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from messagerie_bof where id_message = ?")) {
            pst.setInt(1, this.id);
            pst.executeUpdate();
        }
    }
    
    public static void supMessage(Connection con, int id) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from messagerie_bof where id_message = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }
    
    public static List<messagerie> tousLesMessages(Connection con) throws SQLException {
        List<messagerie> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_message,message,id_operateur from messagerie_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id_message");
                    String mes = rs.getString("message");
                    int id_op = rs.getInt("id_operateur");
                    res.add(new messagerie(id, mes, id_op));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "message{" + "id=" + id + " mes=" + mes + ", id_op=" + id_op + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setId_op(int id_op) {
        this.id_op = id_op;
    }

    public int getId() {
        return id;
    }

    public String getMes() {
        return mes;
    }

    public int getId_op() {
        return id_op;
    }

}
