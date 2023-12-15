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
public class type_etat {

    private int id_type_etat;
    private String des_type_etat;

    private type_etat(int id_type_etat, String des_type_etat) {
        this.id_type_etat = id_type_etat;
        this.des_type_etat = des_type_etat;
    }

    public type_etat(String des_type_etat) {
        this(-1, des_type_etat);
    }

    public void save_type_etat(Connection conn) throws SQLException {
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into type_etat_bof (des_type_etat) values (?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, this.des_type_etat);
            pst.executeUpdate();
       
        try (ResultSet ids = pst.getGeneratedKeys()) {
                ids.next();
                this.id_type_etat = ids.getInt(1);
            }
        }
    }

    @Override
    public String toString() {
        return "type_etat{" + "id_type_etat=" + id_type_etat + ", des_type_etat=" + des_type_etat + '}';
    }

    /**
     * @return the id_type_etat
     */
    public int getId_type_etat() {
        return id_type_etat;
    }

    /**
     * @return the des_type_etat
     */
    public String getDes_type_etat() {
        return des_type_etat;
    }

    /**
     * @param des_type_etat the des_type_etat to set
     */
    public void setDes_type_etat(String des_type_etat) {
        this.des_type_etat = des_type_etat;
    }

}
