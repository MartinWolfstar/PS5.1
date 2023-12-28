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
 * @author melan
 */
public class Type_machine__type_operation {
    private int id_type_machine;
    private int id_type_operation;

    public Type_machine__type_operation(int id_type_machine, int id_type_operation) {
        this.id_type_machine = id_type_machine;
        this.id_type_operation = id_type_operation;
    }
    
    
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into type_machine__type_operation_bof (id_type_machine,id_type_operation) values (?,?)")) {
            pst.setInt(1, this.id_type_machine);
            pst.setInt(2, this.id_type_operation);
            pst.executeUpdate();
        }
    } 
    
    public void sup_type_machine__type_operation (Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from type_machine__type_operation_bof where id_type_machine = ?")) {
            pst.setInt(1, this.id_type_machine);
            pst.executeUpdate();
        }
    }
    public static void sup_type_machine__type_operation (Connection con, int id_type_machine) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from type_machine__type_operation_bof where id_type_machine = ?")) {
            pst.setInt(1, id_type_machine);
            pst.executeUpdate();
        }
    }
    
    
    public static List<Type_machine__type_operation> tousLes_type_machine__type_operation(Connection con) throws SQLException {
        List<Type_machine__type_operation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_type_machine,id_type_operation from type_machine__type_operation_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_type_machine = rs.getInt("id_type_machine");
                    int id_type_operation = rs.getInt("id_type_operation");
                    res.add(new Type_machine__type_operation(id_type_machine, id_type_operation));
                }
            }
        }
        return res;
    }

    public int getId_type_machine() {
        return id_type_machine;
    }

    public int getId_type_operation() {
        return id_type_operation;
    }
    
    @Override
    public String toString() {
        return "Type_machine__type_operation{" + "id_type_machine=" + id_type_machine + ", id_type_operation=" + id_type_operation + '}';
    }

    
}
