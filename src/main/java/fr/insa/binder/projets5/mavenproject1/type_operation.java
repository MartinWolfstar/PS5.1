/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    @Override
    public String toString() {
        return "type_operation{" + "id_type_operation=" + id_type_operation + ", des_type_operation=" + des_type_operation + '}';
    }
    
    /**
     * @return the id_type_operation
     */
    public int getId_type_operation() {
        return id_type_operation;
    }

    /**
     * @return the des_type_operation
     */
    public String getDes_type_operation() {
        return des_type_operation;
    }

    /**
     * @param des_type_operation the des_type_operation to set
     */
    public void setDes_type_operation(String des_type_operation) {
        this.des_type_operation = des_type_operation;
    }
    
    
    
}
