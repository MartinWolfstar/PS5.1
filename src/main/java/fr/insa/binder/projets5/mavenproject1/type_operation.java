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
public class type_operation {
    private int id_type_operation;
    private String des_type_operation;

    private type_operation(int id_type_operation, String des_type_operation) {
        this.id_type_operation = id_type_operation;
        this.des_type_operation = des_type_operation;
    }
    public type_operation(String des_type_operation) {
        this(-1,des_type_operation);
    }
    public void save_type_operation(Connection conn) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into type_operation_bof (des_type_operation) values (?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.des_type_operation);
            pst.executeUpdate();
            try (ResultSet ids = pst.getGeneratedKeys()) {
                ids.next();
                this.id_type_operation = ids.getInt(1);
            }
        }
    }
    
    public static List<type_operation> tousLesTypeOperations(Connection con) throws SQLException {
        List<type_operation> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_type_operation,des_type_operation from type_operation_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_type_operation = rs.getInt("id_type_operation");
                    String des_type_operation = rs.getString("des_type_operation");
                    res.add(new type_operation(id_type_operation, des_type_operation));
                }
            }
        }
        return res;
    }
    
    @Override
    public String toString() {
        return "type_operation{" + "id_type_operation=" + id_type_operation + ", des_type_operation=" + des_type_operation + '}';
    }
    public int getId_type_operation() {
        return id_type_operation;
    }
    public String getDes_type_operation() {
        return des_type_operation;
    }
    public void setDes_type_operation(String des_type_operation) {
        this.des_type_operation = des_type_operation;
    }
    
    
    
}
