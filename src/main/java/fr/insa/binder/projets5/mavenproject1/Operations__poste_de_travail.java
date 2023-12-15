/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

/**
 *
 * @author melan
 */
public class Operations__poste_de_travail {
    private int id_operateur;
    private int id_poste_de_travail;
    
    @Override
    public String toString() {
        return "Operations__poste_de_travail{" + "id_operateur=" + getId_operateur() + ", id_poste_de_travail=" + getId_poste_de_travail() + '}';
    }

    /**
     * @return the id_operateur
     */
    public int getId_operateur() {
        return id_operateur;
    }

    /**
     * @return the id_poste_de_travail
     */
    public int getId_poste_de_travail() {
        return id_poste_de_travail;
    }
    
}
