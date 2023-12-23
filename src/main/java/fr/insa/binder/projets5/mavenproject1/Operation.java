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
    
    private int id_operation;
    private int id_produit;
    private int id_typeOperation;
    
    public Operation(int id_operation, int id_typeOperation, int id_produit) {
        this.id_operation = id_operation;
        this.id_produit = id_produit;
        this.id_typeOperation = id_typeOperation;
    }
    
    public Operation(int id_typeOperation, int id_produit) {
        this(-1, id_typeOperation, id_produit);
    }
    
    public static Operation demande() {
        int id_produit = ConsoleFdB.entreeInt("id_produitroduit : ");
        int id_typeOperation = ConsoleFdB.entreeInt("id_type_operation : ");
        return new Operation(id_typeOperation, id_produit);
    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into operation_bof (id_produit,id_type_operation) values (?,?)")) {
            pst.setInt(1, this.id_produit);
            pst.setInt(2, this.id_typeOperation);
            pst.executeUpdate();
        }
    } 
    
    public void supOperation(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operation_bof where id_operation = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }
    
    public static void supOperation(Connection con, int id_operation) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operation_bof where id_operation = ?")) {
            pst.setInt(1, id_operation);
            pst.executeUpdate();
        }
    }
    
    public static List<Operation> tousLesOperations(Connection con) throws SQLException {
        List<Operation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operation,id_type_operation,id_produit from operation_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation = rs.getInt("id_operation");
                    int id_typeOperation = rs.getInt("id_type_operation");
                    int id_produit = rs.getInt("id_produit");
                    res.add(new Operation(id_operation, id_typeOperation, id_produit));
                }
            }
        }
        return res;
    }
    
    public static List<Operation> tousLesOperations_produit(Connection con, int id_produit) throws SQLException {
        List<Operation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operation,id_type_operation from operation_bof where id_produit=?")) {
            pst.setInt(1, id_produit);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation = rs.getInt("id_operation");
                    int id_typeOperation = rs.getInt("id_type_operation");
                    res.add(new Operation(id_operation, id_typeOperation, id_produit));
                }
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Operation" + "id_operation=" + getId_operation() + ", id_produit=" + getId_produit() + ", id_typeOperation=" + getId_typeOperation() + '}';
    }

    public void setRef(int id_produit) {
        this.id_produit = id_produit;
    }

    public static void setProduit(int id_produit, int id_operation, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update operation_bof set id_produit = ? where id_operation = ?")) {
            pst.setInt(1, id_produit);
            pst.setInt(2, id_operation);            
            pst.executeUpdate();
        }
    }

    public static void setTypeOperation(int id_typeOperation, int id_operation, Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "update operation_bof set id_type_operation = ? where id_operation = ?")) {
            pst.setInt(1, id_typeOperation);
            pst.setInt(2, id_operation);            
            pst.executeUpdate();
        }
    }

    public int getId_operation() {
        return id_operation;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_typeOperation() {
        return id_typeOperation;
    }

    public void setId_operation(int id_operation) {
        this.id_operation = id_operation;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_typeOperation(int id_typeOperation) {
        this.id_typeOperation = id_typeOperation;
    }

    

}

