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
    // Bonjour
    // Bonjour
    
    public void creeSchema() throws SQLException {
        this.conn.setAutoCommit(false);
        try (Statement st = this.conn.createStatement()) {
            st.executeUpdate(
                    "create table machine_bof (\n"
                    + "    id_machine integer not null primary key AUTO_INCREMENT,\n"
                    + "    ref_machine varchar(30) not null,\n"
                    + "    des_machine Text,\n"
                    + " id_poste_de_travail integer not null,\n"
                    + " id_type_machine integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table realise_bof (\n"
                    + "    duree float not null,\n"
                    + "    id_type_operation integer not null,\n"
                    + "    id_machine integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table type_operation_bof (\n"
                    + "    id_type_operation integer not null primary key AUTO_INCREMENT,\n"
                    + "    des_type_operation Text \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table operation_bof (\n"
                    + "    id_operation integer not null primary key AUTO_INCREMENT,\n"
                    + "    id_type_operation integer not null,\n"
                    + "    id_produit integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table produit_bof (\n"
                    + "    id_produit integer not null primary key AUTO_INCREMENT,\n"
                    + "    ref_produit varchar(30) not null,\n"
                    + "    des_produit Text\n"
//                    + "    des_produit Text,\n"
//                    + "    id_commande integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table produit_commande_bof (\n"
                    + "    id_produit integer not null,\n"
                    + "    id_commande integer not null\n"
                    + ")\n"
            );
            
            st.executeUpdate(
                    "alter table realise_bof \n"
                    + "    add constraint fk_realise_bof_id_machine \n"
                    + "    foreign key (id_machine) references machine_bof(id_machine) \n"
            ); 
            
            st.executeUpdate(
                    "alter table realise_bof \n"
                    + "    add constraint fk_realise_bof_id_type_operation \n"
                    + "    foreign key (id_type_operation) references type_operation_bof(id_type_operation) \n"
            ); 
            st.executeUpdate(
                    "alter table operation_bof \n"
                    + "    add constraint fk_operation_bof_id_type_operation \n"
                    + "    foreign key (id_type_operation) references type_operation_bof(id_type_operation) \n"
            ); 
            st.executeUpdate(
                    "alter table operation_bof \n"
                    + "    add constraint fk_operation_bof_id_produit \n"
                    + "    foreign key (id_produit) references produit_bof(id_produit) \n"
            );  
            st.executeUpdate(
                    "create table operateur_bof (\n"
                    + "id_operateur integer primary key AUTO_INCREMENT,\n"
                    + "nom_operateur varchar(50),\n"
                    + "prenom_operateur varchar(40),\n"
                    + "login_operateur varchar(50) not null unique,\n"
                    + "password_operateur varchar(40) not null\n"
                    +")");
            st.executeUpdate(
                    "create table poste_de_travail_bof (\n"
                    + "id_poste_de_travail integer not null primary key,\n"
                    + "ref_poste_de_travail text\n"
                    +")");
            st.executeUpdate(
                    "create table etat_bof (\n"
                    + " id_etat integer not null primary key AUTO_INCREMENT,\n"
                    + " id_type_etat integer not null,\n"
                    + " debut float not null,\n"
                    + " fin float not null\n"
                    +")");
            st.executeUpdate(
                    "create table type_etat_bof (\n"
                    + " id_type_etat integer not null primary key AUTO_INCREMENT,\n"
                    + " des_type_etat text\n"
                    +")");
            st.executeUpdate(
                    "create table client_bof (\n"
                    + " id_client integer not null primary key AUTO_INCREMENT,\n"
                    + "nom_client varchar(50),\n"
                    + "prenom_client varchar(40),\n"
                    + "login_client varchar(50) not null unique,\n"
                    + "password_client varchar(40) not null\n"
                    +")");
            st.executeUpdate(
                    "create table commande_bof (\n"
                    + "id_commande integer primary key AUTO_INCREMENT,\n"
                    + "nom_commande varchar(50),\n"
                    + "des_commande text \n"
                    +")");
            
            st.executeUpdate(
                    "create table type_machine_bof (\n"
                    + "id_type_machine integer primary key,\n"
                    + "des_type_machine text\n"
                    +")");
            st.executeUpdate(
                    "create table exemplaire_bof (\n"
                    + "id_exemplaire integer not null primary key AUTO_INCREMENT,\n"
                    + "des_exemplaire text,\n"
                    + "id_produit integer not null"
                    +")\n"
            );
            st.executeUpdate(
                    "create table operations_effectuees_bof (\n"
                    + "id_operation integer not null,\n"
                    + "id_exemplaire integer not null,\n"
                    + "id_machine integer not null \n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table operations__poste_de_travail_bof (\n"
                    + "id_operateur integer not null,\n"
                    + "id_poste_de_travail integer not null \n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table operateur__etat_bof (\n"
                    + "id_operateur integer not null,\n"
                    + "id_etat integer not null \n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table machine__etat_bof (\n"
                    + "id_machine integer not null,\n"
                    + "id_etat integer not null \n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table type_machine__type_operation_bof (\n"
                    + "id_type_machine integer not null,\n"
                    + "id_type_operation integer not null \n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table precede_bof (\n"
                    + "operation_1 integer,\n"
                    + "operation_2 integer \n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table messagerie_bof (\n"
                    + "id_message integer not null primary key AUTO_INCREMENT,\n"
                    + "id_operateur integer not null,\n"
                    + "message text\n"
                    +")\n"
            );
            st.executeUpdate(
                    "create table commentaire_bof (\n"
                    + "id_commentaire integer not null primary key AUTO_INCREMENT,\n"
                    + "id_produit integer not null,\n"
                    + "id_client integer not null,\n"
                    + "message text\n"
                    +")\n"
            );
            st.executeUpdate(
                    "alter table commentaire_bof \n"
                    + "add constraint fk_commentaire_bof__produit_bof \n"
                    + "foreign key (id_produit) references produit_bof(id_produit)");
            st.executeUpdate(
                    "alter table messagerie_bof \n"
                    + "add constraint fk_messagerie_bof__operateur_bof \n"
                    + "foreign key (id_operateur) references operateur_bof(id_operateur)");
            st.executeUpdate(
                    "alter table precede_bof \n"
                    + "add constraint fk_precede_bof_operation_1 \n"
                    + "foreign key (operation_1) references operation_bof(id_operation)");
            st.executeUpdate(
                    "alter table precede_bof \n"
                    + "add constraint fk_precede_bof_operation_2 \n"
                    + "foreign key (operation_2) references operation_bof(id_operation)");
            st.executeUpdate(
                    "alter table operations__poste_de_travail_bof \n"
                    + "add constraint fk_operations__poste_de_travail_bof_id_operateur \n"
                    + "foreign key (id_operateur) references operateur_bof(id_operateur)");
            st.executeUpdate(
                    "alter table operations__poste_de_travail_bof \n"
                    + "add constraint fk_operations__poste_de_travail_bof_id_poste_de_travail \n"
                    + "foreign key (id_poste_de_travail) references poste_de_travail_bof(id_poste_de_travail)");
            st.executeUpdate(
                    "alter table operateur__etat_bof \n"
                    + "add constraint fk_operateur__etat_bof_id_operateur \n"
                    + "foreign key (id_operateur) references operateur_bof(id_operateur)");
            st.executeUpdate(
                    "alter table operateur__etat_bof \n"
                    + "add constraint fk_operateur__etat_bof_id_etat \n"
                    + "foreign key (id_etat) references etat_bof(id_etat)");
            st.executeUpdate(
                    "alter table machine__etat_bof \n"
                    + "add constraint fk_machine__etat_bof_id_etat \n"
                    + "foreign key (id_etat) references etat_bof(id_etat)");
            st.executeUpdate(
                    "alter table machine__etat_bof \n"
                    + "add constraint fk_machine__etat_bof_id_machine \n"
                    + "foreign key (id_machine) references machine_bof(id_machine)");
            st.executeUpdate(
                    "alter table etat_bof \n"
                    + "add constraint fk_etat_bof_id_type_etat \n"
                    + "foreign key (id_type_etat) references type_etat_bof(id_type_etat)");
            st.executeUpdate(
                    "alter table machine_bof \n"
                    + "add constraint fk_machine_bof_id_poste_de_travail \n"
                    + "foreign key (id_poste_de_travail) references poste_de_travail_bof(id_poste_de_travail)");         
            st.executeUpdate(
                    "alter table type_machine__type_operation_bof \n"
                    + "add constraint fk_type_machine__type_operation_bof_id_type_machine \n"
                    + "foreign key (id_type_machine) references type_machine_bof(id_type_machine)");         
            st.executeUpdate(
                    "alter table type_machine__type_operation_bof \n"
                    + "add constraint fk_type_machine__type_operation_bof_id_type_operation \n"
                    + "foreign key (id_type_operation) references type_operation_bof(id_type_operation)");         
//            st.executeUpdate(
//                    "alter table commande_bof \n"
//                    + "add constraint fk_commande_bof_id_client \n"
//                    + "foreign key (id_client) references client_bof(id_client)");         
//            st.executeUpdate(
//                    "alter table produit_bof \n"
//                    + "add constraint fk_produit_bof_id_commande \n"
//                    + "foreign key (id_commande) references commande_bof(id_commande)");         
//                   
            st.executeUpdate(
                    "alter table exemplaire_bof \n"
                    + "add constraint fk_exemplaire_bof_id_produit \n"
                    + "foreign key (id_produit) references produit_bof(id_produit)");         
            st.executeUpdate(
                    "alter table operations_effectuees_bof \n"
                    + "add constraint fk_operations_effectuees_bof_id_machine \n"
                    + "foreign key (id_machine) references machine_bof(id_machine)");         
            st.executeUpdate(
                    "alter table operations_effectuees_bof \n"
                    + "add constraint fk_operations_effectuees_bof_id_operation \n"
                    + "foreign key (id_operation) references operation_bof(id_operation)");         
            st.executeUpdate(
                    "alter table operations_effectuees_bof \n"
                    + "add constraint fk_operations_effectuees_bof_id_exemplaire \n"
                    + "foreign key (id_exemplaire) references exemplaire_bof(id_exemplaire)");         
            st.executeUpdate(
                    "alter table machine_bof \n"
                    + "add constraint fk_machine_bof_id_type_machine \n"
                    + "foreign key (id_type_machine) references type_machine_bof(id_type_machine)");       
            
            st.executeUpdate(
                    "alter table produit_commande_bof \n"
                    + "add constraint fk_produit_commande_bof_id_produit \n"
                    + "foreign key (id_produit) references produit_bof(id_produit)");
            st.executeUpdate(
                    "alter table produit_commande_bof \n"
                    + "add constraint fk_produit_commande_bof_id_commande \n"
                    + "foreign key (id_commande) references produit_commande_bof(id_commande)");
            
            
            
            this.conn.commit();
        } catch (SQLException ex) {
            this.conn.rollback();
            throw ex;
        } finally {
            this.conn.setAutoCommit(true);
        }
    }
    
    public static void initialise(Connection conn) throws SQLException{
        poste_de_travail poste1 = new poste_de_travail(1, "ranger");
        poste1.save_poste_de_travail(conn);
        type_machine type_machine1 = new type_machine(1, "tournage");
        type_machine1.save_type_machine(conn);
        machine m1 = new machine(333,"tournage", 1, 1);
        m1.saveInDBV1(conn);
        operateur Titi = new operateur( "James", "Einstahitiii", "Titi01", "melissa68");
        Titi.save_operateur(conn);
        //operateur Toto = new operateur( "Dodo", "Einstahitiii", "Dodo68", "123468");
        //Toto.save_operateur(conn);
        type_etat type_etat1 = new type_etat("abscence");
        type_etat1.save_type_etat(conn);
        type_operation type_operation1 = new type_operation("dressage");
        type_operation1.save_type_operation(conn);
        Client client1 = new Client("Binder", "Aurore", "Auroraa", "Aurore");
        Client client2 = new Client("Schmitt", "Theo", "Theo", "Theo");
        Client client3 = new Client("Dalibard", "Melanie", "Melanie", "Melanie");
        client1.saveInDBV(conn);
        client2.saveInDBV(conn);
        client3.saveInDBV(conn);
        commande commande = new commande("Serviette", "POur moi", 1);
        commande commande1 = new commande("Pull", "POur moi", 1);
        commande.saveInDBV1(conn);
        commande1.saveInDBV1(conn);
    }
    
    
    public void deleteSchema() throws SQLException {
        try (Statement st = this.conn.createStatement()) {
            // pour être sûr de pouvoir supprimer, il faut d'abord supprimer les liens
            // puis les tables
            // suppression des liens
            
            try {
                st.executeUpdate("alter table commentaire_bof drop constraint fk_commentaire_bof__produit_bof");
            } catch (SQLException ex) {
                System.out.println("erreur1a" + ex);
            }
            try {
                st.executeUpdate("alter table commentaire_bof drop constraint fk_commentaire_bof_id_commentaire");
            } catch (SQLException ex) {
                System.out.println("erreur1b" + ex);
            }
            try {
                st.executeUpdate("alter table operation_bof drop constraint fk_operation_bof_id_type_operation");
            } catch (SQLException ex) {
                System.out.println("erreur2"+ ex);
            }
            try {
                st.executeUpdate("alter table machine_bof drop constraint fk_machine_bof_id_type_machine");
            } catch (SQLException ex) {
                System.out.println("erreur3"+ ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_exemplaire");
            } catch (SQLException ex) {
                System.out.println("erreur4"+ ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur5"+ ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_machine");
            } catch (SQLException ex) {
                System.out.println("erreur6"+ ex);
            }
            try {
                st.executeUpdate("alter table exemplaire_bof drop constraint fk_exemplaire_bof_id_produit");
            } catch (SQLException ex) {
                System.out.println("erreur7"+ ex);
            }
            try {
                st.executeUpdate("alter table produit_commande_bof drop constraint fk_produit_commande_bof_id_commande");
            } catch (SQLException ex) {
                System.out.println("erreur8"+ ex);
            }
//            try {
//                st.executeUpdate("alter table commande_bof drop constraint fk_commande_bof_id_client");
//            } catch (SQLException ex) {
//            }
//            try {
//                st.executeUpdate("alter table produit_bof drop constraint fk_produit_bof_id_commande");
//            } catch (SQLException ex) {
//            }

            try {
                st.executeUpdate("alter table exemplaire_bof drop constraint fk_exemplaire_bof_id_produit");
            } catch (SQLException ex) {
                System.out.println("erreur9"+ ex);
            }
            
            try {
                st.executeUpdate("alter table type_machine__type_operation_bof drop constraint fk_type_machine__type_operation_bof_id_type_operation");
            } catch (SQLException ex) {
                System.out.println("erreur10"+ ex);
            }
            try {
                st.executeUpdate("alter table type_machine__type_operation_bof drop constraint fk_type_machine__type_operation_bof_id_type_machine");
            } catch (SQLException ex) {
                System.out.println("erreur11"+ ex);
            }
            try {
                st.executeUpdate("alter table machine_bof drop constraint fk_machine_bof_id_poste_de_travail");
            } catch (SQLException ex) {
                System.out.println("erreur12"+ ex);
            }
            try {
                st.executeUpdate("alter table etat_bof drop constraint fk_etat_bof_id_type_etat");
            } catch (SQLException ex) {
                System.out.println("erreur13"+ ex);
            }
            try {
                st.executeUpdate("alter table machine__etat_bof drop constraint fk_machine__etat_bof_id_machine");
            } catch (SQLException ex) {
                System.out.println("erreur14"+ ex);
            }
            try {
                st.executeUpdate("alter table machine__etat_bof drop constraint fk_machine__etat_bof_id_etat");
            } catch (SQLException ex) {
                System.out.println("erreur15"+ ex);
            }
            try {
                st.executeUpdate("alter table operateur__etat_bof drop constraint fk_operateur__etat_bof_id_etat");
            } catch (SQLException ex) {
                System.out.println("erreur16"+ ex);
            }
            try {
                st.executeUpdate("alter table operateur__etat_bof drop constraint fk_operateur__etat_bof_id_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur17"+ ex);
            }
            try {
                st.executeUpdate("alter table operations__poste_de_travail_bof drop constraint fk_operations__poste_de_travail_bof_id_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur18"+ ex);
            }
            try {
                st.executeUpdate("alter table operations__poste_de_travail_bof drop constraint fk_operations__poste_de_travail_bof_id_poste_de_travail");
            } catch (SQLException ex) {
                System.out.println("erreur19"+ ex);
            }
            try {
                st.executeUpdate("alter table precede_bof drop constraint fk_precede_operation_1");
            } catch (SQLException ex) {
                System.out.println("erreur20"+ ex);
            }
            try {
                st.executeUpdate("alter table precede_bof drop constraint fk_precede_operation_2");
            } catch (SQLException ex) {
                System.out.println("erreur21"+ ex);
            }
            try {
                st.executeUpdate("drop table precede_bof");
            } catch (SQLException ex) {
                System.out.println("erreur22"+ ex);
            }
            try {
                st.executeUpdate("drop table precede_bof");
            } catch (SQLException ex) {
                System.out.println("erreur23"+ ex);
            }
            try {
                st.executeUpdate("alter table habilitation_bof drop constraint fk_habilitation_poste_de_travail");
            } catch (SQLException ex) {
                System.out.println("erreur24"+ ex);
            }
            try {
                st.executeUpdate("alter table habilitation_bof drop constraint fk_habilitation_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur25"+ ex);
            }
            try {
                st.executeUpdate("drop table habilitation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur26"+ ex);
            }
            try {
                st.executeUpdate("drop table poste_de_travail_bof");
            } catch (SQLException ex) {
                System.out.println("erreur27"+ ex);
            }
            try {
                st.executeUpdate("drop table operateur_bof");
            } catch (SQLException ex) {
                System.out.println("erreur28"+ ex);
            }
            try {
                st.executeUpdate("alter table messagerie_bof drop constraint fk_messagerie_bof__operateur_bof");
            } catch (SQLException ex) {
                System.out.println("erreur29"+ ex);
            }
            try {
                st.executeUpdate("drop table messagerie_bof");
            } catch (SQLException ex) {
                System.out.println("erreur30"+ ex);
            }
            try {
                st.executeUpdate("alter table utilisateur_bof drop constraint fk_utilisateur_bof_idrole");
            } catch (SQLException ex) {
                System.out.println("erreur31"+ ex);
            }
            try {
                st.executeUpdate("drop table utilisateur_bof");
            } catch (SQLException ex) {
                System.out.println("erreur32"+ ex);
            }
            try {
                st.executeUpdate("drop table role_bof");
            } catch (SQLException ex) {
                System.out.println("erreur33"+ ex);
            }
            try {
                st.executeUpdate("alter table ordre_op_bof drop constraint fk_ordre_op_bof_id_operation_pres");
            } catch (SQLException ex) {
                System.out.println("erreur34"+ ex);
            }
            try {
               st.executeUpdate("alter table ordre_op_bof drop constraint fk_ordre_op_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur35"+ ex);
            }
            try {
               st.executeUpdate("alter table operation_bof drop constraint fk_operation_bof_id_produit");
            } catch (SQLException ex) {
                System.out.println("erreur36"+ ex);
            }
            try {
               st.executeUpdate("alter table type_operation_bof drop constraint fk_type_operation_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur37"+ ex);
            }
            try {
               st.executeUpdate("alter table ordre_op_bof drop constraint fk_type_operation_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur38"+ ex);
            }
            try {
               st.executeUpdate("alter table realise_bof drop constraint fk_realise_bof_id_type_operation");
            } catch (SQLException ex) {
                System.out.println("erreur39"+ ex);
            }
            try {
               st.executeUpdate("alter table realise_bof drop constraint fk_realise_bof_id_machine");
            } catch (SQLException ex) {
                System.out.println("erreur40"+ ex);
            }
            try {
                st.executeUpdate("drop table ordre_op_bof");
            } catch (SQLException ex) {
                System.out.println("erreur42"+ ex);
            }
            try {   
                st.executeUpdate("drop table produit_bof");
            } catch (SQLException ex) {
                System.out.println("erreur43"+ ex);
            }
            try {
                st.executeUpdate("drop table operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur44"+ ex);
            }
            try {
                st.executeUpdate("drop table type_operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur45"+ ex);
            }
            try {
                st.executeUpdate("drop table realise_bof");
            } catch (SQLException ex) {
                System.out.println("erreur46"+ ex);
            }
            try {
                st.executeUpdate("drop table machine_bof");
            } catch (SQLException ex) {
                System.out.println("erreur47"+ ex);
            }
            try {
                st.executeUpdate("drop table Operation");
            } catch (SQLException ex) {
                System.out.println("erreur48"+ ex);
            }
            try {
                st.executeUpdate("drop table machine");
            } catch (SQLException ex) {
                System.out.println("erreur49"+ ex);
            }
            try {
                st.executeUpdate("drop table habilitation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur50"+ ex);
            }
            try {
                st.executeUpdate("drop table ordreoperation");
            } catch (SQLException ex) {
                System.out.println("erreur51"+ ex);
            }
            try {
                st.executeUpdate("drop table produit");
            } catch (SQLException ex) {
                System.out.println("erreur52"+ ex);
            }
            try {
                st.executeUpdate("drop table realisation");
            } catch (SQLException ex) {
                System.out.println("erreur53"+ ex);
            }
            try {
                st.executeUpdate("drop table typeoperation");
            } catch (SQLException ex) {
                System.out.println("erreur54"+ ex);
            }
            try {
                st.executeUpdate("drop table exemplaire_bof");
            } catch (SQLException ex) {
                System.out.println("erreur55"+ ex);
            }
            try {
                st.executeUpdate("drop table type_machine_bof");
            } catch (SQLException ex) {
                System.out.println("erreur56"+ ex);
            }
            try {
                st.executeUpdate("drop table commande_bof");
            } catch (SQLException ex) {
                System.out.println("erreur57"+ ex);
            }
            try {
                st.executeUpdate("drop table client_bof");
            } catch (SQLException ex) {
                System.out.println("erreur58"+ ex);
            }
            try {
                st.executeUpdate("drop table etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur59"+ ex);
            }
            try {
                st.executeUpdate("drop table type_etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur60"+ ex);
            }
            try {
                st.executeUpdate("drop table operations_effectuees_bof");
            } catch (SQLException ex) {
                System.out.println("erreur61"+ ex);
            }
            try {
                st.executeUpdate("drop table operations__poste_de_travail_bof");
            } catch (SQLException ex) {
                System.out.println("erreur62"+ ex);
            }
            try {
                st.executeUpdate("drop table operateur__etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur63"+ ex);
            }
            try {
                st.executeUpdate("drop table machine__etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur64"+ ex);
            }
            try {
                st.executeUpdate("drop table type_machine__type_operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur65"+ ex);
            }
            try {
                st.executeUpdate("drop table operation_operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur66"+ ex);
            }
            try {
                st.executeUpdate("drop table produit_commande_bof");
            } catch (SQLException ex) {
                System.out.println("erreur67"+ ex);
            }
            try {
                st.executeUpdate("drop table commentaire_bof");
            } catch (SQLException ex) {
                System.out.println("erreur68"+ ex);
            }
            
        }  
    }   
    
    public void razBdD(Connection conn) throws SQLException{
        this.deleteSchema();
        this.creeSchema();
        initialise(conn);
                
    }

    public void menuMachine() {
        int rep = -1;
        while (rep != 0) {
            int i = 1;
            System.out.println("Menu machine");
            System.out.println("================");
            System.out.println((i++) + ") supprimer schéma");
            System.out.println((i++) + ") créer schéma");
            System.out.println((i++)+ ") initialiser");
            System.out.println((i++) +") Raz de la BdD = supprimer + creer + initialiser");
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
                    initialise(conn);
                } else if (rep == j++) {
                    razBdD(conn);
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
                System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa", 5));
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
