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
public class Precede {
    
    private int id_operation_1;
    private int id_operation_2;
    
    public Precede(int id_operation_1, int id_operation_2) {
        this.id_operation_1 = id_operation_1;
        this.id_operation_2 = id_operation_2;
    }
    
//    public Operation(int id_operation_2, int id_produit) {
//        this(-1, id_operation_2, id_produit);
//    }
    
//    public static Operation demande() {
//        int id_produit = ConsoleFdB.entreeInt("id_produitroduit : ");
//        int id_operation_2 = ConsoleFdB.entreeInt("id_type_operation : ");
//        return new Operation(id_operation_2, id_produit);
//    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into precede_bof (operation_1,operation_2) values (?,?)")) {
            pst.setInt(1, this.id_operation_1);
            pst.setInt(2, this.id_operation_2);
            pst.executeUpdate();
        }
    } 
    
    public void supPrecede(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where id_operation_1 = ?")) {
            pst.setInt(1, this.id_operation_1);
            pst.executeUpdate();
        }
    }
    
    public static void supPrecede(Connection con, int id_operation_1) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where operation_1 = ?")) {
            pst.setInt(1, id_operation_1);
            pst.executeUpdate();
        }
    }
    
    public static void supPrecede(Connection con, int id_operation_1, int id_operation_2) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from precede_bof where operation_1 = ? and operation_2 = ?")) {
            pst.setInt(1, id_operation_1);
            pst.setInt(2, id_operation_2);            
            pst.executeUpdate();
        }
    }
    
    public static List<Precede> tousLesPrecede(Connection con) throws SQLException {
        List<Precede> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select operation_1, operation_2 from precede_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation_1 = rs.getInt("operation_1");
                    int id_operation_2 = rs.getInt("operation_2");
                    res.add(new Precede(id_operation_1, id_operation_2));
                }
            }
        }
        return res;
    }
    
    public static List<Precede> tousLesPrecede_operation(Connection con, int id_operation_1) throws SQLException {
        List<Precede> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select operation_2 from precede_bof where operation_1=?")) {
            pst.setInt(1, id_operation_1);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation_2 = rs.getInt("operation_2");
                    res.add(new Precede(id_operation_1, id_operation_2));
                }
            }
        }
        return res;
    }
    
    public static String liste_to_string (List<Precede> liste) {
        String st = "";
        for (Precede pre : liste) {
            st = st + "  " + String.valueOf(pre.getId_operation_2());
        }
        return st;
    }

//    @Override
//    public String toString() {
//        return "Operation" + "id_operation_1=" + getid_operation_1() + ", id_produit=" + getId_produit() + ", id_operation_2=" + getid_operation_2() + '}';
//    }


//    public static void setProduit(int id_produit, int id_operation_1, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "update operation_bof set id_produit = ? where id_operation_1 = ?")) {
//            pst.setInt(1, id_produit);
//            pst.setInt(2, id_operation_1);            
//            pst.executeUpdate();
//        }
//    }
//
//    public static void setTypeOperation(int id_operation_2, int id_operation_1, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "update operation_bof set id_type_operation = ? where id_operation_1 = ?")) {
//            pst.setInt(1, id_operation_2);
//            pst.setInt(2, id_operation_1);            
//            pst.executeUpdate();
//        }
//    }

    public int getId_operation_1() {
        return id_operation_1;
    }

    public void setId_operation_1(int id_operation_1) {
        this.id_operation_1 = id_operation_1;
    }

    public int getId_operation_2() {
        return id_operation_2;
    }

    public void setId_operation_2(int id_operation_2) {
        this.id_operation_2 = id_operation_2;
    }



    
}
