/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                "insert into type_operation_bof (id_type_operation,des_type_operation) values (?,?)")) {
            pst.setInt(1, this.id_type_operation);
            pst.setString(2, this.des_type_operation);
            pst.executeUpdate();
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
