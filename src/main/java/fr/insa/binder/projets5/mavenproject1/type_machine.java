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
public class type_machine {
    private int id_type_machine;
    private String des_type_machine;

    public type_machine(int id_type_machine, String des_type_machine) {
        this.id_type_machine = id_type_machine;
        this.des_type_machine = des_type_machine;
    }
    public void save_type_machine(Connection conn) throws SQLException{
        try (PreparedStatement pst = conn.prepareStatement(
                "insert into type_machine_bof (id_type_machine,des_type_machine) values (?,?)")) {
            pst.setInt(1, this.id_type_machine);
            pst.setString(2, this.des_type_machine);
            pst.executeUpdate();
        }
    }
    
        public static List<type_machine> tousLesTypeMachine(Connection con) throws SQLException {
        List<type_machine> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_type_machine,des_type_machine from type_machine_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_type_machine = rs.getInt("id_type_machine");
                    String des_type_machine = rs.getString("des_type_machine");
                    res.add(new type_machine(id_type_machine, des_type_machine));
                }
            }
        }
        return res;
    }

    public int getId_type_machine() {
        return id_type_machine;
    }

    public String getDes_type_machine() {
        return des_type_machine;
    }

    public void setDes_type_machine(String des_type_machine) {
        this.des_type_machine = des_type_machine;
    }

    @Override
    public String toString() {
        return "type_machine{" + "id_type_machine=" + id_type_machine + ", des_type_machine=" + des_type_machine + '}';
    }
    
}
