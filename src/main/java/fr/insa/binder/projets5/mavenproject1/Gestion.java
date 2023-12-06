/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abinder01
 */
public class Gestion {
    private Connection conn;

    public Gestion(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public static Connection connectGeneralMySQL(String host,
            int port, String database,
            String user, String pass)
            throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port
                + "/" + database,
                user, pass);
        con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return con;
    }
    
    public static Connection connectSurServeurM3() throws SQLException {
        return connectGeneralMySQL("92.222.25.165", 3306,
                "m3_abinder01", "m3_abinder01",
                "c7b7bc39");
    }
    
    public void creeSchema() throws SQLException {
        this.conn.setAutoCommit(false);
        try (Statement st = this.conn.createStatement()) {
            st.executeUpdate(
                    "create table machine_bof (\n"
                    + "    id_m integer not null primary key AUTO_INCREMENT,\n"
                    + "    ref_m varchar(30) not null,\n"
                    + "    des_m Text\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table realise_bof (\n"
                    + "    duree float not null,\n"
                    + "    id_to integer not null,\n"
                    + "    id_m integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table type_operation_bof (\n"
                    + "    id_to integer not null primary key AUTO_INCREMENT,\n"
                    + "    des_to Text,\n"
                    + "    id_o integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table operation_bof (\n"
                    + "    id_o integer not null primary key AUTO_INCREMENT,\n"
                    + "    id_to integer not null,\n"
                    + "    id_p integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table produit_bof (\n"
                    + "    id_p integer not null primary key AUTO_INCREMENT,\n"
                    + "    ref_m varchar(30) not null,\n"
                    + "    des_m Text\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table ordre_op_bof (\n"
                    + "    id_o integer not null,\n"
                    + "    id_o_pres integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "alter table realise_bof \n"
                    + "    add constraint fk_realise_bof_id_m \n"
                    + "    foreign key (id_m) references machine_bof(id_m) \n"
            ); 
            st.executeUpdate(
                    "alter table realise_bof \n"
                    + "    add constraint fk_realise_bof_id_to \n"
                    + "    foreign key (id_to) references type_operation_bof(id_to) \n"
            ); 
            st.executeUpdate(
                    "alter table type_operation_bof \n"
                    + "    add constraint fk_type_operation_bof_id_o \n"
                    + "    foreign key (id_o) references operation_bof(id_o) \n"
            ); 
            st.executeUpdate(
                    "alter table operation_bof \n"
                    + "    add constraint fk_operation_bof_id_p \n"
                    + "    foreign key (id_p) references produit_bof(id_p) \n"
            );  
            st.executeUpdate(
                    "alter table ordre_op_bof \n"
                    + "    add constraint fk_ordre_op_bof_id_o \n"
                    + "    foreign key (id_o) references operation_bof(id_o) \n"
            );  
            st.executeUpdate(
                    "alter table ordre_op_bof \n"
                    + "    add constraint fk_ordre_op_bof_id_o_pres \n"
                    + "    foreign key (id_o_pres) references operation_bof(id_o) \n"
            );
            st.executeUpdate(
                    "create table role_bof (\n"
                    + "id integer primary key AUTO_INCREMENT,\n"
                    + "nom varchar(20),\n"
                    + "des text \n"
                    +")");
            st.executeUpdate(
                    "create table utilisateur_bof (\n"
                    + "id integer primary key AUTO_INCREMENT,\n"
                    + "login varchar(50),\n"
                    + "password varchar(40),\n"
                    + "des text, \n"
                    + "idrole integer\n"
                    +")");
            st.executeUpdate(
                    "alter table utilisateur_bof \n"
                    + " add constraint fk_utilisateur_bof_idrole \n"
                    + " foreign key (idrole) references role_bof(id) \n");
            st.executeUpdate(
                    "create table operateur_bof (\n"
                    + "id integer primary key AUTO_INCREMENT,\n"
                    + "nom varchar(50),\n"
                    + "prenom varchar(40)\n"
                    +")");
            st.executeUpdate(
                    "create table poste_de_travail_bof (\n"
                    + "id integer primary key AUTO_INCREMENT,\n"
                    + "ref text\n"
                    +")");
            st.executeUpdate(
                    "create table habilitation_bof (\n"
                    + " operateur integer,\n"
                    + "poste_de_travail integer"
                    +")");
            st.executeUpdate(
                    "alter table habilitation_bof \n"
                    + "add constraint fk_habilitation_bof_operateur \n"
                    + "foreign key (operateur) references operateur_bof(id)");
            st.executeUpdate(
                    "alter table habilitation_bof \n"
                    + "add constraint fk_habilitation_bof_poste_de_travail \n"
                    + "foreign key (poste_de_travail) references poste_de_travail_bof(id)");
            st.executeUpdate(
                    "create table etat_bof (\n"
                    + " id_type_etat integer not null primary key AUTO_INCREMENT,\n"
                    + " debut float not null,\n"
                    + " fin float not null,\n"
                    +")");
            st.executeUpdate(
                    "create table type_etat_bof (\n"
                    + " id_type integer not null primary key AUTO_INCREMENT,\n"
                    + " des text,\n"
                    +")");
            st.executeUpdate(
                    "create client_bof (\n"
                    + " id_client integer not null primary key AUTO_INCREMENT,\n"
                    + "nom_client varchar(50),\n"
                    + "prenom_client varchar(40),\n"
                    +")");
            st.executeUpdate(
                    "create table commande_bof (\n"
                    + "id_commande integer primary key AUTO_INCREMENT,\n"
                    + "nom_commande varchar(50),\n"
                    + "des_commande text, \n"
                    +")");
            st.executeUpdate(
                    "create table type_machine_bof (\n"
                    + "id_type_machine integer primary key AUTO_INCREMENT,\n"
                    + "des text,\n"
                    +")");
            st.executeUpdate(
                    "create table exemplaire_bof (\n"
                    + "id integer primary key AUTO_INCREMENT,\n"
                    +")");
            st.executeUpdate(
                    "create table macchhiinnee (\n"
                    + "id integer primary key AUTO_INCREMENT,\n"
                    +")");
            this.conn.commit();
        } catch (SQLException ex) {
            this.conn.rollback();
            throw ex;
        } finally {
            this.conn.setAutoCommit(true);
        }
    }
    
    public void deleteSchema() throws SQLException {
        try (Statement st = this.conn.createStatement()) {
            // pour être sûr de pouvoir supprimer, il faut d'abord supprimer les liens
            // puis les tables
            // suppression des liens

            try {
                st.executeUpdate("alter table habilitation_bof drop constraint fk_habilitation_poste_de_travail");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("alter table habilitation_bof drop constraint fk_habilitation_operateur");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table habilitation_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table poste_de_travail_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table operateur_bof");
            } catch (SQLException ex) {
            }
            
            try {
                st.executeUpdate("alter table utilisateur_bof drop constraint fk_utilisateur_bof_idrole");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table utilisateur_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table role_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("alter table ordre_op_bof drop constraint fk_ordre_op_bof_id_o_pres");
            } catch (SQLException ex) {
            }
            try {
               st.executeUpdate("alter table ordre_op_bof drop constraint fk_ordre_op_bof_id_o");
            } catch (SQLException ex) {
            }
            try {
               st.executeUpdate("alter table operation_bof drop constraint fk_operation_bof_id_p");
            } catch (SQLException ex) {
            }
            try {
               st.executeUpdate("alter table type_operation_bof drop constraint fk_type_operation_bof_id_o");
            } catch (SQLException ex) {
            }
            try {
               st.executeUpdate("alter table ordre_op_bof drop constraint fk_type_operation_bof_id_o");
            } catch (SQLException ex) {
            }
            try {
               st.executeUpdate("alter table realise_bof drop constraint fk_realise_bof_id_to");
            } catch (SQLException ex) {
            }
            try {
               st.executeUpdate("alter table realise_bof drop constraint fk_realise_bof_id_m");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table ordre_op_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table produit_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table operation_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table type_operation_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table realise_bof");
            } catch (SQLException ex) {
            }
            try {
                st.executeUpdate("drop table machine_bof");
            } catch (SQLException ex) {
            }
        }  
    }   
    
    

    public void menuMachine() {
        int rep = -1;
        while (rep != 0) {
            int i = 1;
            System.out.println("Menu machine");
            System.out.println("================");
            System.out.println((i++) + ") supprimer schéma");
            System.out.println((i++) + ") créer schéma");
            System.out.println((i++) + ") lister les machines");
            System.out.println((i++) + ") ajouter un machine");
            //System.out.println((i++) + ") chercher par pattern");
            System.out.println("0) Fin");
            rep = ConsoleFdB.entreeEntier("Votre choix : ");
            try {
                int j = 1;
                if (rep == j++) {
                    this.deleteSchema();
                } else if (rep == j++) {
                    this.creeSchema();
                } else if (rep == j++) {
                    List<machine> users = machine.tousLesMachines(this.conn);
                    System.out.println(users.size() + " utilisateurs : ");
                    for (int k = 0; k < users.size(); k++) {   
                        System.out.print(users.get(k));
                    }  
                    //System.out.println(ListUtils.enumerateList(users));
                } else if (rep == j++) {
                    System.out.println("entrez un nouvel utilisateur : ");
                    machine nouveau = machine.demande();
                    nouveau.saveInDBV1(this.conn);
                /*} else if (rep == j++) {
                    this.afficheUtilisateurAvecPattern();*/
                }
            } catch (SQLException ex) {
                System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa.binder.lol", 5));
            }
        }
    }
        
    public static void debut(){
        try {
            Connection con = connectSurServeurM3();
            System.out.println("Connecté");
            Gestion gestionnaire = new Gestion(con);
            gestionnaire.menuMachine();
        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        debut();
    }
}
