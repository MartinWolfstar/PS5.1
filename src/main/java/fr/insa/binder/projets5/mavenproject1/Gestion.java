/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import fr.insa.binder.projets5.mavenproject1.Utilitaire.ListUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

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
                    + "    id_machine integer not null primary key AUTO_INCREMENT,\n"
                    + "    ref_machine varchar(30) not null,\n"
                    + "    des_machine Text,\n"
                    + " id_poste_de_travail integer not null,\n"
                    + " id_type_machine integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                "CREATE TABLE ImageT (\n" +
                "    id_image INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
                "    nom VARCHAR(255) NOT NULL,\n" +
                "    image LONGBLOB\n" +
                ")"
            );

            st.executeUpdate(
                    "create table realise_bof (\n"
                    + "    id_realisation integer not null primary key AUTO_INCREMENT,\n"
                    + "    duree float not null,\n"
                    + "    id_type_operation integer not null,\n"
                    + "    id_machine integer not null\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "CREATE TABLE type_operation_bof ("
                    + "id_type_operation INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "des_type_operation TEXT, "
                    + "UNIQUE (des_type_operation(255))"
                    + ")"
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
                    + ")");
            st.executeUpdate(
                    "create table poste_de_travail_bof (\n"
                    + "id_poste_de_travail integer not null primary key AUTO_INCREMENT,\n"
                    + "ref_poste_de_travail text,\n"
                    + "x1 integer,\n"
                    + "x2 integer,\n"
                    + "y1 integer,\n"
                    + "y2 integer\n"
                    + ")");
            st.executeUpdate(
                    "create table etat_bof (\n"
                    + " id_etat integer not null primary key AUTO_INCREMENT,\n"
                    + " id_type_etat integer not null,\n"
                    + " debut TIMESTAMP,\n"
                    + " fin TIMESTAMP\n"
                    + ")");
            st.executeUpdate(
                    "create table type_etat_bof (\n"
                    + " id_type_etat integer not null primary key AUTO_INCREMENT,\n"
                    + " des_type_etat text, "
                    + " unique (des_type_etat(255))"
                    + ")");
            st.executeUpdate(
                    "create table client_bof (\n"
                    + " id_client integer not null primary key AUTO_INCREMENT,\n"
                    + "nom_client varchar(50),\n"
                    + "prenom_client varchar(40),\n"
                    + "login_client varchar(50) not null unique,\n"
                    + "adresse_client varchar(50),\n"
                    + "telephone_client varchar(50),\n"
                    + "mail_client varchar(50),\n"
                    + "password_client varchar(40) not null\n"
                    + ")");
            st.executeUpdate(
                    "create table commande_bof (\n"
                    + "id_commande integer primary key AUTO_INCREMENT,\n"
                    + "nom_commande varchar(50),\n"
                    + "des_commande text, \n"
                    + "id_client integer not null"
                    + ")\n");

            st.executeUpdate(
                    "create table type_machine_bof (\n"
                    + "id_type_machine integer primary key AUTO_INCREMENT,\n"
                    + "des_type_machine text,"
                    + "unique (des_type_machine(255))"
                    + ")");
            st.executeUpdate(
                    "create table exemplaire_bof (\n"
                    + "id_exemplaire integer not null primary key AUTO_INCREMENT,\n"
                    + "des_exemplaire text,\n"
                    + "id_produit integer not null, \n"
                    + "id_commande integer not null"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table operations_effectuees_bof (\n"
                    + "id_operation integer not null,\n"
                    + "id_exemplaire integer not null,\n"
                    + "id_machine integer not null, \n"
                    + "id_operateur integer not null, \n"
                    + "debut TIMESTAMP,\n"
                    + "fin TIMESTAMP\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table operations__poste_de_travail_bof (\n"
                    + "id_operateur integer not null,\n"
                    + "id_poste_de_travail integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table operateur__etat_bof (\n"
                    + "id_operateur integer not null,\n"
                    + "id_etat integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table machine__etat_bof (\n"
                    + "id_machine integer not null,\n"
                    + "id_etat integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table type_machine__type_operation_bof (\n"
                    + "id_type_machine integer not null,\n"
                    + "id_type_operation integer not null \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table precede_bof (\n"
                    + "operation_1 integer,\n"
                    + "operation_2 integer \n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table messagerie_bof (\n"
                    + "id_message integer not null primary key AUTO_INCREMENT,\n"
                    + "id_operateur integer not null,\n"
                    + "message text\n"
                    + ")\n"
            );
            st.executeUpdate(
                    "create table commentaire_bof (\n"
                    + "id_commentaire integer not null primary key AUTO_INCREMENT,\n"
                    + "id_produit integer not null,\n"
                    + "id_client integer not null,\n"
                    + "message text\n"
                    + ")\n"
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
            st.executeUpdate(
                    "alter table commande_bof \n"
                    + "add constraint fk_commande_bof_id_client \n"
                    + "foreign key (id_client) references client_bof(id_client)");
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
                    "alter table exemplaire_bof \n"
                    + "add constraint fk_exemplaire_bof_id_commande \n"
                    + "foreign key (id_commande) references commande_bof(id_commande)");

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
                    "alter table operations_effectuees_bof \n"
                    + "add constraint fk_operations_effectuees_bof_id_operateur \n"
                    + "foreign key (id_operateur) references operateur_bof(id_operateur)");
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
                    + "foreign key (id_commande) references commande_bof(id_commande)");

            this.conn.commit();
        } catch (SQLException ex) {
            this.conn.rollback();
            throw ex;
        } finally {
            this.conn.setAutoCommit(true);
        }
    }

    public static void initialise(Connection conn) throws SQLException {
        // postes de travail
        poste_de_travail poste1 = new poste_de_travail("ranger",30,200,30,200);
        poste1.save_poste_de_travail(conn);
        poste_de_travail poste2 = new poste_de_travail("poussiereux",300,350,60,90);
        poste2.save_poste_de_travail(conn);
        poste_de_travail poste3 = new poste_de_travail("brillant",500,700,70,320);
        poste3.save_poste_de_travail(conn);
        
        // types de machines
//        type_machine type_machine1 = new type_machine("tournage");
//        type_machine1.save_type_machine(conn);
//        type_machine type_machine2 = new type_machine("fraisage");
//        type_machine2.save_type_machine(conn);
        type_machine type_machine1 = new type_machine("Impréssionator 3000");
        type_machine1.save_type_machine(conn);

        type_machine type_machine2 = new type_machine("Reliure-licious");
        type_machine2.save_type_machine(conn);

        type_machine type_machine3 = new type_machine("Assemble-o-matic"); 
        type_machine3.save_type_machine(conn);

        type_machine type_machine4 = new type_machine("Éditron Deluxe");
        type_machine4.save_type_machine(conn);

        type_machine type_machine5 = new type_machine("Emballage Extravaganza");
        type_machine5.save_type_machine(conn);

        type_machine type_machine6 = new type_machine("Distribu-tron 5000"); 
        type_machine6.save_type_machine(conn);

        type_machine type_machine7 = new type_machine("Promo-blitzinator"); 
        type_machine7.save_type_machine(conn);

        type_machine type_machine8 = new type_machine("Réviseur Rigolo"); 
        type_machine8.save_type_machine(conn);

        
        // Machines
//        machine m1 = new machine(333, "tour tres puissant", 1, 1);
//        m1.saveInDBV1(conn);
//        machine m2 = new machine(444,"tour tres precis",1,1);
//        m2.saveInDBV1(conn);
//        machine m3 = new machine(555,"fraise rapide",2,2);
//        m3.saveInDBV1(conn);
//        machine m4 = new machine(666,"fraise petits calibres",3,2);
//        m4.saveInDBV1(conn);
        machine m1 = new machine(333, "Tour de Puissance Extrême", 1, 1);
        m1.saveInDBV1(conn);
        machine m2 = new machine(444, "Tour de Précision Suprême", 1, 1);
        m2.saveInDBV1(conn);
        machine m3 = new machine(555, "Fraise Rapide Ultra", 2, 2);
        m3.saveInDBV1(conn);
        machine m4 = new machine(666, "Fraise Petits Calibres Magique", 3, 2);
        m4.saveInDBV1(conn);
        machine m5 = new machine(777, "L'Imprimeur Fantastique", 1, 1);
        m5.saveInDBV1(conn);
        machine m6 = new machine(888, "Relieur Magique", 2, 2);
        m6.saveInDBV1(conn);
        machine m7 = new machine(999, "Fraise Turbo Drôle", 3, 2);
        m7.saveInDBV1(conn);
        machine m8 = new machine(1010, "Assembleur Loufoque", 2, 3);
        m8.saveInDBV1(conn);
        machine m9 = new machine(1111, "Éditeur Éclair", 1, 4);
        m9.saveInDBV1(conn);
        machine m10 = new machine(1212, "Emballage Enchanté", 2, 5);
        m10.saveInDBV1(conn);
        machine m11 = new machine(1313, "Distribu-Buzz", 3, 6);
        m11.saveInDBV1(conn);
        machine m12 = new machine(1414, "Promo-Fiesta", 2, 7);
        m12.saveInDBV1(conn);

        
        
        // Operateurs
        operateur Titi = new operateur("James", "Einstahitiii", "Titi01", "melissa68");
        Titi.save_operateur(conn);
        operateur Toto = new operateur( "Dodo", "Einstahitiii", "Dodo68", "123468");
        Toto.save_operateur(conn);
        
        // type etat
        type_etat type_etat2 = new type_etat("abscent/en panne");
        type_etat2.save_type_etat(conn);
        type_etat type_etat1 = new type_etat("present/fonctionnel");
        type_etat1.save_type_etat(conn);
        
        // etats
        LocalDateTime dt = LocalDateTime.of(2021, Month.DECEMBER, 3,15, 0, 23);
        Timestamp ts = Timestamp.valueOf(dt);
        LocalDateTime dt2 = LocalDateTime.of(2023, Month.DECEMBER, 3,15, 0, 23);
        Timestamp ts2 = Timestamp.valueOf(dt2);
        etat etat1 = new etat(1, ts, ts2);
        etat1.save_etat(conn);
        
        LocalDateTime dt3 = LocalDateTime.of(2023,Month.OCTOBER,1,8,0,0);
        Timestamp ts3 = Timestamp.valueOf(dt3);
        LocalDateTime dt4 = LocalDateTime.of(2023,Month.NOVEMBER,16,18,30,00);
        Timestamp ts4 = Timestamp.valueOf(dt4);
        etat etat2 = new etat(2,ts3,ts4);
        etat2.save_etat(conn);
        
        etat etat3 = new etat(1,Timestamp.valueOf(LocalDateTime.of(2021,Month.NOVEMBER,16,18,30,00)),Timestamp.valueOf(LocalDateTime.of(2023,Month.JANUARY,8,18,30,00)));
        etat3.save_etat(conn);
        
        // type operation
//        type_operation type_operation1 = new type_operation("dressage");
//        type_operation1.save_type_operation(conn);
//        type_operation type_operation2 = new type_operation("chariotage");
//        type_operation2.save_type_operation(conn);
//        type_operation type_operation3 = new type_operation("chanfreinage");
//        type_operation3.save_type_operation(conn);
//        type_operation type_operation4 = new type_operation("rainurage");
//        type_operation4.save_type_operation(conn);
//        type_operation type_operation5 = new type_operation("tronconnage");
//        type_operation5.save_type_operation(conn);
        type_operation impression = new type_operation("Impression");
        type_operation reliure = new type_operation("Reliure");
        type_operation assemblage = new type_operation("Assemblage");
        type_operation édition = new type_operation("Édition");
        type_operation emballage = new type_operation("Emballage");
        type_operation distribution = new type_operation("Distribution");
        type_operation promotion = new type_operation("Promotion");
        type_operation révision = new type_operation("Révision");
        type_operation traduction = new type_operation("Traduction");
        type_operation adaptation = new type_operation("Adaptation");
        impression.save_type_operation(conn);
        reliure.save_type_operation(conn);
        assemblage.save_type_operation(conn);
        édition.save_type_operation(conn);
        emballage.save_type_operation(conn);
        distribution.save_type_operation(conn);
        promotion.save_type_operation(conn);
        révision.save_type_operation(conn);
        traduction.save_type_operation(conn);
        adaptation.save_type_operation(conn);

        // clients
        Client client1 = new Client("Binder", "Aurore", "Auroraa", "Aurore");
        Client client2 = new Client("Schmitt", "Theo", "Theo", "Theo");
        Client client3 = new Client("Dalibard", "Melanie", "Melanie", "Melanie");
        client1.saveInDBV(conn);
        client2.saveInDBV(conn);
        client3.saveInDBV(conn);
        
        // produit
        produit p1 = new produit("les cigognes dans leur habitat naturel",  1);
        produit p2 = new produit("Blanche Neige et les 6 personnes de petites tailles",  2);
        produit p3 = new produit("La personne au bois dormant",  3);
        produit p4 = new produit("Comment dresser votre dragon de compagnie invisible", 4);
        produit p5 = new produit( "Le guide ultime pour comprendre le langage des pingouins", 5);
        produit p6 = new produit( "Les aventures secrètes du chat ninja dans la nuit", 6);
        produit p7 = new produit( "L'art subtil de faire la sieste en réunion", 7);
        produit p8 = new produit( "Les canards en affaires : de la mare au conseil d'administration", 8);
        produit p9 = new produit( "Yoga pour girafes : atteindre de nouveaux sommets de détente", 9);
        produit p10 = new produit( "Les lutins et le management : guide pratique pour une entreprise magique", 10);
        produit p11 = new produit( "Les extraterrestres ont-ils un sens de l'humour ?", 11);
        produit p12 = new produit( "L'histoire secrète des licornes en politique", 12);
        produit p13 = new produit( "Le manuel complet de survie face à une invasion de canards en peluche", 13);
        produit p14 = new produit( "Les conseils du koala pour une vie zen", 14);
        produit p15 = new produit( "Les vaches qui méditent : une approche bovine de la sérénité", 15);
        produit p16 = new produit( "Comment dresser un hamster pour le marathon", 16);
        produit p17 = new produit( "Le guide pratique pour élever des licornes dans un appartement", 17);
        produit p18 = new produit( "Les secrets du succès selon les chèvres grimpeuses", 18);
        produit p19 = new produit( "L'art de jongler avec des pommes de terre chaudes", 19);
        produit p20 = new produit( "Les aventures hilarantes du pingouin philosophe", 20);
        produit p21 = new produit( "Comment dresser votre dragon de compagnie invisible, deuxième édition", 21);
        produit p22 = new produit( "Le manuel pratique du ninja retraité", 22);
        produit p23 = new produit("Les canards en affaires : de la mare au conseil d'administration, version actualisée", 23);
        produit p24 = new produit( "Yoga pour girafes : atteindre de nouveaux sommets de détente, version illustrée", 24);
        p1.saveInDBV1(conn);
        p2.saveInDBV1(conn);
        p3.saveInDBV1(conn);
        p4.saveInDBV1(conn);
        p5.saveInDBV1(conn);
        p6.saveInDBV1(conn);
        p7.saveInDBV1(conn);
        p8.saveInDBV1(conn);
        p9.saveInDBV1(conn);
        p10.saveInDBV1(conn);
        p11.saveInDBV1(conn);
        p12.saveInDBV1(conn);
        p13.saveInDBV1(conn);
        p14.saveInDBV1(conn);
        p15.saveInDBV1(conn);
        p16.saveInDBV1(conn);
        p17.saveInDBV1(conn);
        p18.saveInDBV1(conn);
        p19.saveInDBV1(conn);
        p20.saveInDBV1(conn);
        p21.saveInDBV1(conn);
        p22.saveInDBV1(conn);
        p23.saveInDBV1(conn);
        p24.saveInDBV1(conn);

        //operation
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j <= 10; j++) {
                Operation operation = new Operation(j, i);
                operation.saveInDBV1(conn);
            }
        }
        
//        commande commande = new commande("Serviette", "POur moi", 1);
//        commande commande1 = new commande("Pull", "POur moi", 1);
//        commande.saveInDBV1(conn);
//        commande1.saveInDBV1(conn);
        
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
                System.out.println("erreur2" + ex);
            }
            try {
                st.executeUpdate("alter table machine_bof drop constraint fk_machine_bof_id_type_machine");
            } catch (SQLException ex) {
                System.out.println("erreur3" + ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_exemplaire");
            } catch (SQLException ex) {
                System.out.println("erreur4" + ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur4" + ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur5" + ex);
            }
            try {
                st.executeUpdate("alter table operations_effectuees_bof drop constraint fk_operations_effectuees_bof_id_machine");
            } catch (SQLException ex) {
                System.out.println("erreur6" + ex);
            }
            try {
                st.executeUpdate("alter table exemplaire_bof drop constraint fk_exemplaire_bof_id_produit");
            } catch (SQLException ex) {
                System.out.println("erreur7" + ex);
            }
            try {
                st.executeUpdate("alter table exemplaire_bof drop constraint fk_exemplaire_bof_id_commande");
            } catch (SQLException ex) {
                System.out.println("erreur7" + ex);
            }
            try {
                st.executeUpdate("alter table produit_commande_bof drop constraint fk_produit_commande_bof_id_commande");
            } catch (SQLException ex) {
                System.out.println("erreur8" + ex);
            }

            try {
                st.executeUpdate("alter table produit_commande_bof drop constraint fk_produit_commande_bof_id_produit");
            } catch (SQLException ex) {
                System.out.println("erreur8" + ex);
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
                System.out.println("erreur9" + ex);
            }

            try {
                st.executeUpdate("alter table type_machine__type_operation_bof drop constraint fk_type_machine__type_operation_bof_id_type_operation");
            } catch (SQLException ex) {
                System.out.println("erreur10" + ex);
            }
            try {
                st.executeUpdate("alter table type_machine__type_operation_bof drop constraint fk_type_machine__type_operation_bof_id_type_machine");
            } catch (SQLException ex) {
                System.out.println("erreur11" + ex);
            }
            try {
                st.executeUpdate("alter table machine_bof drop constraint fk_machine_bof_id_poste_de_travail");
            } catch (SQLException ex) {
                System.out.println("erreur12" + ex);
            }
            try {
                st.executeUpdate("alter table etat_bof drop constraint fk_etat_bof_id_type_etat");
            } catch (SQLException ex) {
                System.out.println("erreur13" + ex);
            }
            try {
                st.executeUpdate("alter table machine__etat_bof drop constraint fk_machine__etat_bof_id_machine");
            } catch (SQLException ex) {
                System.out.println("erreur14" + ex);
            }
            try {
                st.executeUpdate("alter table machine__etat_bof drop constraint fk_machine__etat_bof_id_etat");
            } catch (SQLException ex) {
                System.out.println("erreur15" + ex);
            }
            try {
                st.executeUpdate("alter table operateur__etat_bof drop constraint fk_operateur__etat_bof_id_etat");
            } catch (SQLException ex) {
                System.out.println("erreur16" + ex);
            }
            try {
                st.executeUpdate("alter table operateur__etat_bof drop constraint fk_operateur__etat_bof_id_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur17" + ex);
            }
            try {
                st.executeUpdate("alter table operations__poste_de_travail_bof drop constraint fk_operations__poste_de_travail_bof_id_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur18" + ex);
            }
            try {
                st.executeUpdate("alter table operations__poste_de_travail_bof drop constraint fk_operations__poste_de_travail_bof_id_poste_de_travail");
            } catch (SQLException ex) {
                System.out.println("erreur19" + ex);
            }
            try {
                st.executeUpdate("alter table precede_bof drop constraint fk_precede_operation_1");
            } catch (SQLException ex) {
                System.out.println("erreur20" + ex);
            }
            try {
                st.executeUpdate("alter table precede_bof drop constraint fk_precede_operation_2");
            } catch (SQLException ex) {
                System.out.println("erreur21" + ex);
            }
            try {
                st.executeUpdate("drop table precede_bof");
            } catch (SQLException ex) {
                System.out.println("erreur22" + ex);
            }
            try {
                st.executeUpdate("drop table precede_bof");
            } catch (SQLException ex) {
                System.out.println("erreur23" + ex);
            }
            try {
                st.executeUpdate("alter table habilitation_bof drop constraint fk_habilitation_poste_de_travail");
            } catch (SQLException ex) {
                System.out.println("erreur24" + ex);
            }
            try {
                st.executeUpdate("alter table habilitation_bof drop constraint fk_habilitation_operateur");
            } catch (SQLException ex) {
                System.out.println("erreur25" + ex);
            }
            try {
                st.executeUpdate("drop table habilitation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur26" + ex);
            }
            try {
                st.executeUpdate("drop table poste_de_travail_bof");
            } catch (SQLException ex) {
                System.out.println("erreur27" + ex);
            }

            try {
                st.executeUpdate("alter table messagerie_bof drop constraint fk_messagerie_bof__operateur_bof");
            } catch (SQLException ex) {
                System.out.println("erreur29" + ex);
            }

            try {
                st.executeUpdate("drop table operateur_bof");
            } catch (SQLException ex) {
                System.out.println("erreur28" + ex);
            }

            try {
                st.executeUpdate("drop table messagerie_bof");
            } catch (SQLException ex) {
                System.out.println("erreur30" + ex);
            }
            try {
                st.executeUpdate("alter table utilisateur_bof drop constraint fk_utilisateur_bof_idrole");
            } catch (SQLException ex) {
                System.out.println("erreur31" + ex);
            }
            try {
                st.executeUpdate("drop table utilisateur_bof");
            } catch (SQLException ex) {
                System.out.println("erreur32" + ex);
            }
            try {
                st.executeUpdate("drop table role_bof");
            } catch (SQLException ex) {
                System.out.println("erreur33" + ex);
            }
            try {
                st.executeUpdate("alter table ordre_op_bof drop constraint fk_ordre_op_bof_id_operation_pres");
            } catch (SQLException ex) {
                System.out.println("erreur34" + ex);
            }
            try {
                st.executeUpdate("alter table ordre_op_bof drop constraint fk_ordre_op_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur35" + ex);
            }
            try {
                st.executeUpdate("alter table operation_bof drop constraint fk_operation_bof_id_produit");
            } catch (SQLException ex) {
                System.out.println("erreur36" + ex);
            }
            try {
                st.executeUpdate("alter table type_operation_bof drop constraint fk_type_operation_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur37" + ex);
            }
            try {
                st.executeUpdate("alter table ordre_op_bof drop constraint fk_type_operation_bof_id_operation");
            } catch (SQLException ex) {
                System.out.println("erreur38" + ex);
            }
            try {
                st.executeUpdate("alter table realise_bof drop constraint fk_realise_bof_id_type_operation");
            } catch (SQLException ex) {
                System.out.println("erreur39" + ex);
            }
            try {
                st.executeUpdate("alter table realise_bof drop constraint fk_realise_bof_id_machine");
            } catch (SQLException ex) {
                System.out.println("erreur40" + ex);
            }
            try {
                st.executeUpdate("drop table ordre_op_bof");
            } catch (SQLException ex) {
                System.out.println("erreur42" + ex);
            }
            try {
                st.executeUpdate("drop table produit_bof");
            } catch (SQLException ex) {
                System.out.println("erreur43" + ex);
            }
            try {
                st.executeUpdate("drop table operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur44" + ex);
            }
            try {
                st.executeUpdate("drop table type_operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur45" + ex);
            }
            try {
                st.executeUpdate("drop table realise_bof");
            } catch (SQLException ex) {
                System.out.println("erreur46" + ex);
            }
            try {
                st.executeUpdate("drop table machine_bof");
            } catch (SQLException ex) {
                System.out.println("erreur47" + ex);
            }
            try {
                st.executeUpdate("drop table Operation");
            } catch (SQLException ex) {
                System.out.println("erreur48" + ex);
            }
            try {
                st.executeUpdate("drop table machine");
            } catch (SQLException ex) {
                System.out.println("erreur49" + ex);
            }
            try {
                st.executeUpdate("drop table habilitation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur50" + ex);
            }
            try {
                st.executeUpdate("drop table ordreoperation");
            } catch (SQLException ex) {
                System.out.println("erreur51" + ex);
            }
            try {
                st.executeUpdate("drop table produit");
            } catch (SQLException ex) {
                System.out.println("erreur52" + ex);
            }
            try {
                st.executeUpdate("drop table realisation");
            } catch (SQLException ex) {
                System.out.println("erreur53" + ex);
            }
            try {
                st.executeUpdate("drop table typeoperation");
            } catch (SQLException ex) {
                System.out.println("erreur54" + ex);
            }
            try {
                st.executeUpdate("drop table exemplaire_bof");
            } catch (SQLException ex) {
                System.out.println("erreur55" + ex);
            }
            try {
                st.executeUpdate("drop table type_machine_bof");
            } catch (SQLException ex) {
                System.out.println("erreur56" + ex);
            }
            try {
                st.executeUpdate("drop table commande_bof");
            } catch (SQLException ex) {
                System.out.println("erreur57" + ex);
            }
            try {
                st.executeUpdate("drop table client_bof");
            } catch (SQLException ex) {
                System.out.println("erreur58" + ex);
            }
            try {
                st.executeUpdate("drop table etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur59" + ex);
            }
            try {
                st.executeUpdate("drop table type_etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur60" + ex);
            }
            try {
                st.executeUpdate("drop table operations_effectuees_bof");
            } catch (SQLException ex) {
                System.out.println("erreur61" + ex);
            }
            try {
                st.executeUpdate("drop table operations__poste_de_travail_bof");
            } catch (SQLException ex) {
                System.out.println("erreur62" + ex);
            }
            try {
                st.executeUpdate("drop table operateur__etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur63" + ex);
            }
            try {
                st.executeUpdate("drop table machine__etat_bof");
            } catch (SQLException ex) {
                System.out.println("erreur64" + ex);
            }
            try {
                st.executeUpdate("drop table type_machine__type_operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur65" + ex);
            }
            try {
                st.executeUpdate("drop table operation_operation_bof");
            } catch (SQLException ex) {
                System.out.println("erreur66" + ex);
            }
            try {
                st.executeUpdate("drop table produit_commande_bof");
            } catch (SQLException ex) {
                System.out.println("erreur67" + ex);
            }
            try {
                st.executeUpdate("drop table commentaire_bof");
            } catch (SQLException ex) {
                System.out.println("erreur68" + ex);
            }
            try {
                st.executeUpdate("drop table ImageT");
            } catch (SQLException ex) {
                System.out.println("erreur23" + ex);
            }

        }
    }

    public void razBdD(Connection conn) throws SQLException {
        this.deleteSchema();
        this.creeSchema();
        initialise(conn);

    }

    public void menuPrincipal() {
        int rep = -1;
        while (rep != 0) {
            int i = 1;
            System.out.println("Menu principal");
            System.out.println("================");
            System.out.println((i++) + ") supprimer schéma (= supprimer la base de donnee)");
            System.out.println((i++) + ") créer schéma(=créer la base de donnee)");
            System.out.println((i++) + ") initialiser la base de donnees");
            System.out.println((i++) + ") Raz de la BdD = supprimer + creer + initialiser");
            System.out.println((i++) + ") lister les machines");
            System.out.println((i++) + ") ajouter un machine");
            System.out.println((i++) + ") menu un machine");
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
                    machine nouveau = machine.demande2(conn);
                    nouveau.saveInDBV1(this.conn);
                    /*} else if (rep == j++) {
                    this.afficheUtilisateurAvecPattern();*/
                }
                else if (rep == j++) {
                    menuMachine();
                }
            } catch (SQLException ex) {
                System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa", 5));
            }
        }
    }
    public void menuMachine() {
        int rep = -1;
        while (rep != 0) {
            int i = 1;
            System.out.println("Menu machine");
            System.out.println("================");
            System.out.println((i++) + ") liste les machines");
            System.out.println((i++) + ") ajouter une machine");
            System.out.println((i++) + ") supprimer une machine");
            //System.out.println((i++) + ") chercher par pattern");
            System.out.println("0) Fin");
            rep = ConsoleFdB.entreeEntier("Votre choix : ");
            try {
                int j = 1;
                if (rep == j++) {
                    System.out.println(ListUtils.enumerateList(machine.tousLesMachines(conn)));
                } else if (rep == j++) {
                    machine nouvelle = machine.demande2(conn);
                    nouvelle.saveInDBV1(conn);
                }
                else if (rep == j++) {
                    Optional<machine> choix = ListUtils.selectOneOrCancel("---- selectionnez une machine à supprimer",machine.tousLesMachines(conn), machine::toString);
                    if (choix.isPresent()){
                        choix.get().supMachine(conn);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ExceptionsUtils.messageEtPremiersAppelsDansPackage(ex, "fr.insa", 5));
            }
        }
    }

    public static void debut() {
        try {
            Connection con = connectSurServeurM3();
            System.out.println("Connecté");
            Gestion gestionnaire = new Gestion(con);
            gestionnaire.menuPrincipal();
        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }

    public static void main(String[] args) {
        debut();
    }
}
