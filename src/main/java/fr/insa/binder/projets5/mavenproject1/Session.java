/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;


/**
 *
 * @author abinder01
 */
public class Session{
    private int id_perso_connecte;
    private Connection conn;

    public Session() {
        this.id_perso_connecte = -1;
        this.conn = null;
    }
    
    /**
     * @return the id_perso_connecte
     */
    public int getId_perso_connecte() {
        return id_perso_connecte;
    }

    /**
     * @param id_perso_connecte the id_perso_connecte to set
     */
    public void setId_perso_connecte(int id_perso_connecte) {
        this.id_perso_connecte = id_perso_connecte;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    
}