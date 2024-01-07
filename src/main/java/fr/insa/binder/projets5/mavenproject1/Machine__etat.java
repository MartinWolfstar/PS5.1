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
public class Machine__etat {
    private int id_machine;
    private int id_etat;

    public Machine__etat(int id_machine, int id_etat) {
        this.id_machine = id_machine;
        this.id_etat = id_etat;

    }
    
    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into machine__etat_bof (id_machine,id_etat) values (?,?)")) {
            pst.setInt(1, this.id_machine);
            pst.setInt(2, this.id_etat);
            pst.executeUpdate();
        }
    } 
    
    public void supMachine__etat(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from machine__etat_bof where id_machine = ?")) {
            pst.setInt(1, this.id_machine);
            pst.executeUpdate();
        }
    }
    
    public static void supMachine__etat(Connection con, int id_machine) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from machine__etat_bof where id_machine = ?")) {
            pst.setInt(1, id_machine);
            pst.executeUpdate();
        }
    }
    
    public static List<Machine__etat> tousLesMachine__etat(Connection con) throws SQLException {
        List<Machine__etat> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_machine,id_etat from machine__etat_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_machine = rs.getInt("id_machine");
                    int id_etat = rs.getInt("id_etat");
                    res.add(new Machine__etat(id_machine, id_etat));
                }
            }
        }
        return res;
    }

    public int getId_machine() {
        return id_machine;
    }

    public int getId_etat() {
        return id_etat;
    }

    @Override
    public String toString() {
        return "Machine__etat{" + "id_machine=" + id_machine + ", id_etat=" + id_etat + '}';
    }
    
}
