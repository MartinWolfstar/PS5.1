/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;

import static fr.insa.binder.projets5.mavenproject1.Gestion.connectSurServeurM3;
import static fr.insa.binder.projets5.mavenproject1.Operation.Machine_operation;
import static fr.insa.binder.projets5.mavenproject1.Precede.tousLesOrdreOperations_produit;
import static fr.insa.binder.projets5.mavenproject1.etat.tousLesEtats_m;
import static fr.insa.binder.projets5.mavenproject1.etat.tousLesEtats_op;
import static fr.insa.binder.projets5.mavenproject1.exemplaire.get_ex;
import static fr.insa.binder.projets5.mavenproject1.machine.Liste_habilitation;
import static fr.insa.binder.projets5.mavenproject1.operateur.tousLesOperateur;
import static fr.insa.binder.projets5.mavenproject1.realisation.getDuree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author binde
 */
public class operation_effectuee {

    private int id_operation;
    private int id_exemplaire;
    private int id_machine;
    private int id_operateur;
    private Timestamp debut;
    private Timestamp fin;

    private operation_effectuee(int id_operation, int id_exemplaire, int id_machine, int id_operateur) {
        this.id_operation = id_operation;
        this.id_exemplaire = id_exemplaire;
        this.id_machine = id_machine;
        this.id_operateur = id_operateur;
    }

    private operation_effectuee(int id_operation, int id_exemplaire, int id_machine, int id_operateur, Timestamp debut, Timestamp fin) {
        this.id_operation = id_operation;
        this.id_exemplaire = id_exemplaire;
        this.id_machine = id_machine;
        this.id_operateur = id_operateur;
        this.debut = debut;
        this.fin = fin;
    }

    public operation_effectuee(operation_effectuee original) {
        // Initialise les champs de la nouvelle instance avec les valeurs de l'instance existante
        this.id_exemplaire = original.id_exemplaire;
        this.id_operation = original.id_operation;
        this.id_machine = original.id_machine;
        this.id_operateur = original.id_operateur;
        this.debut = new Timestamp(original.debut.getTime());
        this.fin = new Timestamp(original.fin.getTime());        // ... initialisez d'autres champs de manière similaire ...
    }

    public void saveInDBV1(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "insert into operations_effectuees_bof (id_operation, id_exemplaire, id_machine, id_operateur, debut, fin) values (?,?,?,?,?,?)")) {
            pst.setInt(1, this.id_operation);
            pst.setInt(2, this.id_exemplaire);
            pst.setInt(3, this.id_machine);
            pst.setInt(4, this.id_operateur);
            pst.setTimestamp(5, this.debut);
            pst.setTimestamp(6, this.fin);
            pst.executeUpdate();

        }
    }

    public void sup_operation_effectuee_op(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_operation = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }

    public void sup_operation_effectuee_ex(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_exemplaire = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }

    public void sup_operation_effectuee_ma(Connection con) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_machine = ?")) {
            pst.setInt(1, this.id_operation);
            pst.executeUpdate();
        }
    }

    public static void sup_operation_effectuee_op(Connection con, int id_operation) throws SQLException {
        try (PreparedStatement pst = con.prepareStatement(
                "delete from operations_effectuees_bof where id_operation = ?")) {
            pst.setInt(1, id_operation);
            pst.executeUpdate();
        }
    }

    public static List<operation_effectuee> tous_les_operation_effectuees(Connection con) throws SQLException {
        List<operation_effectuee> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operation, id_machine, id_exemplaire, id_operateur from operations_effectuees_bof")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation = rs.getInt("id_operation");
                    int id_machine = rs.getInt("id_machine");
                    int id_exemplaire = rs.getInt("id_exemplaire");
                    int id_operateur = rs.getInt("id_operateur");
                    res.add(new operation_effectuee(id_operation, id_exemplaire, id_machine, id_operateur));
                }
            }
        }
        return res;
    }

    public static List<operation_effectuee> tous_les_operation_effectuees_ex(Connection con, int id_ex) throws SQLException {
        List<operation_effectuee> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select id_operation, id_machine, id_exemplaire, id_operateur, debut, fin from operations_effectuees_bof where id_exemplaire = ?")) {
            pst.setInt(1, id_ex);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int id_operation = rs.getInt("id_operation");
                    int id_machine = rs.getInt("id_machine");
                    int id_exemplaire = rs.getInt("id_exemplaire");
                    int id_operateur = rs.getInt("id_operateur");
                    Timestamp debut = rs.getTimestamp("debut");
                    Timestamp fin = rs.getTimestamp("fin");
                    res.add(new operation_effectuee(id_operation, id_exemplaire, id_machine, id_operateur, debut, fin));
                }
            }
        }
        return res;
    }

    public static float Duree_liste_op(List<Operation> liste, Connection con) throws SQLException {
        float duree = 0;
        float min = 1000000;
        for (Operation op : liste) {
            realisation realise = getDuree(op.getId_typeOperation(), con);
            duree = duree + realise.getDuree();
            System.out.println(duree);
        }
        if (duree < min) {
            min = duree;
            System.out.print("Nouveau min" + min);
        }
        return min;
    }

    public static void Save_bdd(List<operation_effectuee> liste, Connection con) throws SQLException {
        for (operation_effectuee op : liste) {
            op.saveInDBV1(con);
        }
    }

    public static void Save_m_bdd(List<operation_effectuee> liste, Connection con) throws SQLException {
        
        List<List<Timestamp>> time_out = new ArrayList<>();
        List<Integer> id_m = new ArrayList<>();
        for (operation_effectuee op : liste) {
            List<Timestamp> temps = new ArrayList<>();
            temps.add(op.getDebut());
            temps.add(op.getFin());
            time_out.add(temps);
            id_m.add(op.getId_machine());
        }
        HashSet<Integer> ensembleSansDoublons = new HashSet<>(id_m);
        List<Integer> listeSansDoublons = new ArrayList<>(ensembleSansDoublons);
        System.out.println("Operateur travaillant " + listeSansDoublons);
        for(int i : listeSansDoublons){
            List<List<Timestamp>> nouvelle_dispo = new ArrayList<>();
            List<List<Timestamp>> time_in = Disponiblité_machine(i, con);
            System.out.println("Ancienne dispo " + time_in);
            System.out.println("Nouvelle non dispo " + time_out);
            nouvelle_dispo = Verif_dispo(time_in, time_out);
            System.out.println("Nouvelle dispo " + nouvelle_dispo);
            List<etat>  etat = tousLesEtats_m(con, i);
            System.out.println(etat);
            for (etat e : etat)  {
                e.supEtat(con);
                System.out.println("Supp_etat");
            }
            for (List<Timestamp> liste_time_in : nouvelle_dispo){
                 etat etat_present = new etat(2, liste_time_in.get(0), liste_time_in.get(1));
                 etat_present.save_etat(con);
                 Machine__etat op_etat = new Machine__etat(i,etat_present.getId_etat());
                 op_etat.saveInDBV1(con);
            }
        }
        
        
    }
    
    public static void Save_op_bdd(List<operation_effectuee> liste, Connection con) throws SQLException {
        
        List<List<Timestamp>> time_out = new ArrayList<>();
        List<Integer> id_op = new ArrayList<>();
        for (operation_effectuee op : liste) {
            List<Timestamp> temps = new ArrayList<>();
            temps.add(op.getDebut());
            temps.add(op.getFin());
            time_out.add(temps);
            id_op.add(op.getId_operateur());
        }
        HashSet<Integer> ensembleSansDoublons = new HashSet<>(id_op);
        List<Integer> listeSansDoublons = new ArrayList<>(ensembleSansDoublons);
        System.out.println("Operateur travaillant " + listeSansDoublons);
        for(int i : listeSansDoublons){
            List<List<Timestamp>> nouvelle_dispo = new ArrayList<>();
            List<List<Timestamp>> time_in = Disponiblité_operateur(i, con);
            System.out.println("Ancienne dispo " + time_in);
            System.out.println("Nouvelle non dispo " + time_out);
            nouvelle_dispo = Verif_dispo(time_in, time_out);
            System.out.println("Nouvelle dispo " + nouvelle_dispo);
            List<etat>  etat = tousLesEtats_op(con, i);
            System.out.println(etat);
            for (etat e : etat)  {
                e.supEtat(con);
                System.out.println("Supp_etat");
            }
            for (List<Timestamp> liste_time_in : nouvelle_dispo){
                 etat etat_present = new etat(2, liste_time_in.get(0), liste_time_in.get(1));
                 etat_present.save_etat(con);
                 Operateur__etat op_etat = new Operateur__etat(i,etat_present.getId_etat());
                 op_etat.saveInDBV1(con);
            }
        }
        
        
    }

    public static List<operation_effectuee> Meilleurs_operation_produit(Connection con, exemplaire exempl) throws SQLException {
//        Timestamp time = new Timestamp(System.currentTimeMillis());
        List<List<Operation>> liste = tousLesOrdreOperations_produit(con, exempl.getId_produit());
        List<operation_effectuee> meilleur_liste = new ArrayList<>();
        Operation operation = liste.get(0).get(0);
        float min = 1000000000;
//        float min = getDuree(operation.getId_typeOperation(), con).getDuree();
        System.out.print(min);
        for (List<Operation> liste_op : liste) {
            long duree = 0;
            List<operation_effectuee> operation_eff = new ArrayList<>();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            Timestamp time_bis = time;
            System.out.println("TIME :" + time);
            for (Operation op : liste_op) {
                operation_effectuee eff = meilleure_operation_eff(op, con, time);
                System.out.println(operation_eff);
                if (eff.getId_operation() == -1) {
                    System.out.println("Impossible a produire !");
                    operation_eff.clear();
                    break;
                }
                operation_effectuee eff_2 = new operation_effectuee(eff);
                long temps = eff.Temps_timestamp();
                time_bis.setTime(time_bis.getTime() + eff.Temps_timestamp_1());
                long temps_2 = eff.Temps_timestamp_1();
                duree = duree + temps_2;
                eff_2.setId_exemplaire(exempl.getId_exemplaire());
                operation_eff.add(eff_2);
                System.out.println(duree);
            }
            if ((duree < min) && (!operation_eff.isEmpty())) {
                min = duree;
                System.out.print("Nouveau min" + min);
                meilleur_liste = operation_eff;
            }
        }
        Save_bdd(meilleur_liste, con);
        Save_op_bdd(meilleur_liste, con);
        //Save_m_bdd(meilleur_liste, con);
        return meilleur_liste;
    }

    public static operation_effectuee meilleure_operation_eff(Operation op, Connection con, Timestamp time) throws SQLException {
        System.out.println("meilleure op eff");

        List<realisation> real = Machine_operation(op, con);
        operation_effectuee op_eff = new operation_effectuee(-1, 0, 0, 0);
        long min = 1705179261046L * 2;
        for (realisation re : real) {
            List<Integer> id_op = Liste_habilitation(re.getId_machine(), con);
            for (Integer id : id_op) {
                List<Timestamp> debut_fin = Premiere_dispo(time, re.getDuree(), re.getId_machine(), id, con);
                if (debut_fin.isEmpty()) {
                    System.out.println("Plus de disponibilitées communes");
                } else if (debut_fin.get(1).getTime() < min) {
                    op_eff.setId_machine(re.getId_machine());
                    op_eff.setId_operateur(id);
                    op_eff.setId_operation(op.getId_operation());

                    op_eff.setDebut(debut_fin.get(0));
                    op_eff.setFin(debut_fin.get(1));
                }
            }
        }
        System.out.println(op_eff + "Theo");
        return op_eff;
    }

    public long Temps_timestamp() {
        return this.fin.getTime() - this.debut.getTime();
    }

    public long Temps_timestamp_1() {
        return this.fin.getTime() - System.currentTimeMillis();
    }

    public static long Temps_timestamp(List<Timestamp> list) {
        return list.get(1).getTime() - list.get(0).getTime();
    }

    public static long Temps_timestamp(Timestamp debut, Timestamp fin) {
        return fin.getTime() - debut.getTime();
    }

    //Devrait renvoyé la premiere disponibilité d'un couple machine operateur 
    public static List<Timestamp> Premiere_dispo(Timestamp time, float duree, int id_m, int id_op, Connection con) throws SQLException {
        System.out.println("Premiere dispo");

        List<Timestamp> temps_premier = new ArrayList<>();

        List<List<Timestamp>> disponnibilite_m = Disponiblité_machine(id_m, con);
        disponnibilite_m = Disponibilité_pour_duree(duree, disponnibilite_m, time);
        Collections.sort(disponnibilite_m, Comparator.comparing(liste -> liste.get(0)));
        System.out.println(disponnibilite_m);
        System.out.println(disponnibilite_m);

        List<List<Timestamp>> disponnibilite_op = Disponiblité_operateur(id_op, con);
        disponnibilite_op = Disponibilité_pour_duree(duree, disponnibilite_op, time);
        Collections.sort(disponnibilite_op, Comparator.comparing(liste -> liste.get(0)));
        System.out.println(disponnibilite_op);

        // Plus de disponibilitées
        if (disponnibilite_m.isEmpty() || disponnibilite_op.isEmpty()) {
            return temps_premier;
        } // l'operateur n'est pas disponible sur la duree proposée
        else if (Dispo_debut_fin_op(duree, disponnibilite_op, disponnibilite_m.get(0).get(0), disponnibilite_m.get(0).get(1)).isEmpty()) {
            return Premiere_dispo(disponnibilite_op.get(0).get(0), duree, id_m, id_op, con);
        } else {
            Timestamp debut_2 = disponnibilite_op.get(0).get(0);
            long fin_2 = (long) (debut_2.getTime() + duree * 60 * 1000);
            Timestamp fin = new Timestamp(fin_2);
            temps_premier.add(debut_2);
            temps_premier.add(fin);
            return temps_premier;
        }
    }

//    public static List<List<Timestamp>> Verif_dispo(List<List<Timestamp>> libre, List<List<Timestamp>> deja_occupe) {
//        List<List<Timestamp>> newList = new ArrayList<>();
//        for (List<Timestamp> dispo : libre) {
//            Timestamp debut_l = dispo.get(0);
//            Timestamp fin_l = dispo.get(1);
//            for (List<Timestamp> occ : deja_occupe) {
//                Timestamp debut_o = occ.get(0);
//                Timestamp fin_o = occ.get(1);
//                if ((debut_l > debut_o) && (fin_l<debut_o)){
//                    
//                }
//            }
//        }
//    }
// renvoie une liste de timestamp (debut et fin) de toutes les plages de disponibilité de la machine. 
    public static List<List<Timestamp>> Disponiblité_machine(int id_m, Connection con) throws SQLException {
        System.out.println("Dispo_machine");

        List<List<Timestamp>> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select debut, fin from etat_bof join machine__etat_bof on etat_bof.id_etat = machine__etat_bof.id_etat where id_machine = ? and etat_bof.id_type_etat = 2")) {
            pst.setInt(1, id_m);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    List<Timestamp> time = new ArrayList<>();
                    Timestamp debut = rs.getTimestamp("debut");
                    Timestamp fin = rs.getTimestamp("fin");
                    time.add(debut);
                    time.add(fin);
                    res.add(time);
                }
            }
        }
        System.out.println(res);
        return res;
    }

    // Renvoie normalement la liste des paires de timestamp (debut et fin), qui convienne pour la durée, et a partir d'un certain moment
    public static List<List<Timestamp>> Disponibilité_pour_duree(float duree, List<List<Timestamp>> liste, Timestamp debut) {
        System.out.println("Dispo_pour_duree");

        System.out.println("liste dispo machine :");

        System.out.println(liste);
        System.out.println(duree);

        List<List<Timestamp>> res = new ArrayList<>();
        for (List<Timestamp> liste_tst : liste) {
            System.out.println("Aurore" + liste_tst);
            Timestamp fin_t = liste_tst.get(1);
            Timestamp debut_t = liste_tst.get(1);
            System.out.println(fin_t);
            System.out.println("r" + ((fin_t.getTime() - debut.getTime()) / (1000 * 60)));
            if (((fin_t.getTime() - debut.getTime()) / (1000 * 60) >= duree) || (((fin_t.getTime() - debut_t.getTime()) >= duree) && (debut.getTime() <= debut_t.getTime()))) {
                if (liste_tst.get(0).getTime() < debut.getTime()) {
                    liste_tst.set(0, debut);
                }
                res.add(liste_tst);
            }
        }

        System.out.println("res" + res);
        return res;
    }

    // regarder si un operateur est diponible entre deux timestamp sur une certaine duree. Renvoie le moment ou il est dispo. (debut + fin)
    public static List<Timestamp> Dispo_debut_fin_op(float duree, List<List<Timestamp>> liste, Timestamp debut, Timestamp fin) {
        System.out.println("Dispo_debut_fin_operateur");

        List<Timestamp> res = new ArrayList<>();
        List<List<Timestamp>> liste_pas_classee = new ArrayList<>();
        for (List<Timestamp> liste_tst : liste) {
            long debut_op = liste_tst.get(0).getTime();
            long fin_op = liste_tst.get(1).getTime();
            if ((debut_op <= debut.getTime()) && (fin_op >= fin.getTime())) {
                liste_pas_classee.add(liste_tst);
            } else if ((debut_op >= debut.getTime()) && ((debut_op / (1000 * 60)) + duree < (fin.getTime() / (1000 * 60)))) {
                liste_pas_classee.add(liste_tst);
            } else if ((fin_op <= fin.getTime()) && ((fin_op / (1000 * 60)) - duree) > debut.getTime()) {
                liste_pas_classee.add(liste_tst);
            }

        }
        Collections.sort(liste_pas_classee, Comparator.comparing(list -> list.get(0)));
        System.out.println("liste_pas_classe" + liste_pas_classee);
        if (liste_pas_classee.isEmpty()) {
            return new ArrayList<>();
        } else {
            return liste_pas_classee.get(0);

        }
    }

    // Idem mais avec les operateurs
    public static List<List<Timestamp>> Disponiblité_operateur(int id_op, Connection con) throws SQLException {
        System.out.println("Dispo_operateur");
        List<List<Timestamp>> res = new ArrayList<>();
        try (PreparedStatement pst = con.prepareStatement(
                "select debut, fin from etat_bof join operateur__etat_bof on etat_bof.id_etat = operateur__etat_bof.id_etat where id_operateur = ? and etat_bof.id_type_etat = 2")) {
            pst.setInt(1, id_op);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    List<Timestamp> time = new ArrayList<>();
                    Timestamp debut = rs.getTimestamp("debut");
                    Timestamp fin = rs.getTimestamp("fin");
                    time.add(debut);
                    time.add(fin);
                    res.add(time);
                }
            }
        }
        System.out.println(res);
        return res;
    }

    @Override
    public String toString() {

        return "operation_effectuee" + "id_operation=" + getId_operation() + ", id_exemplaire=" + getId_exemplaire() + ", id_machine=" + getId_machine() + "debut" + getDebut() + "Fin" + getFin();
    }

//     public static void set_id_operation(int id_operation, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "updatoperation_effectuee_bof set reoperation_effectuee = ? where ioperation_effectuee = ?")) {
//            pst.setInt(1, id_exemplaire);
//            pst.setInt(2, id_operation);            
//            pst.executeUpdate();
//        }
//    }
//    
//
//    public static void set_id_exemplaire(int id_exemplaire, int id_operation, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "updatoperation_effectuee_bof set reoperation_effectuee = ? where ioperation_effectuee = ?")) {
//            pst.setInt(1, id_exemplaire);
//            pst.setInt(2, id_operation);            
//            pst.executeUpdate();
//        }
//    }
//    public static void set_id_machine(String des, int id_operation, Connection con) throws SQLException {
//        try (PreparedStatement pst = con.prepareStatement(
//                "updatoperation_effectuee_bof set deoperation_effectuee = ? where ioperation_effectuee = ?")) {
//            pst.setString(1, des);
//            pst.setInt(2, id_operation);            
//            pst.executeUpdate();
//        }
//    }
    public int getId_operation() {
        return id_operation;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public Timestamp getFin() {
        return fin;
    }

    public void setFin(Timestamp fin) {
        this.fin = fin;
    }

    public void setId_operation(int id_operation) {
        this.id_operation = id_operation;
    }

    public int getId_exemplaire() {
        return id_exemplaire;
    }

    public void setId_exemplaire(int id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public int getId_machine() {
        return id_machine;
    }

    public void setId_machine(int id_machine) {
        this.id_machine = id_machine;
    }

    public int getId_operateur() {
        return id_operateur;
    }

    public void setId_operateur(int id_operateur) {
        this.id_operateur = id_operateur;
    }

    public static List<Timestamp> extraireDeuxiemeColonne(List<List<Timestamp>> listeDeDeuxColonnes) {
        List<Timestamp> deuxiemeColonne = new ArrayList<>();

        for (List<Timestamp> paire : listeDeDeuxColonnes) {
            // Assurez-vous qu'il y a au moins deux éléments dans la paire
            if (paire.size() >= 2) {
                deuxiemeColonne.add(paire.get(1));
            }
        }

        return deuxiemeColonne;
    }

    public static List<Timestamp> extrairePremierColonne(List<List<Timestamp>> listeDeDeuxColonnes) {
        List<Timestamp> deuxiemeColonne = new ArrayList<>();

        for (List<Timestamp> paire : listeDeDeuxColonnes) {
            // Assurez-vous qu'il y a au moins deux éléments dans la paire
            if (paire.size() >= 2) {
                deuxiemeColonne.add(paire.get(0));
            }
        }

        return deuxiemeColonne;
    }

    public static List<List<Timestamp>> Verif_dispo(List<List<Timestamp>> libre, List<List<Timestamp>> deja_occupe) {
        List<Timestamp> libre_d = extrairePremierColonne(libre);
        List<Timestamp> libre_f = extraireDeuxiemeColonne(libre);
        List<Timestamp> deja_occupe_d = extrairePremierColonne(deja_occupe);
        List<Timestamp> deja_occupe_f = extraireDeuxiemeColonne(deja_occupe);
        List<List<Timestamp>> newList = new ArrayList<>();
        for (Timestamp time : libre_d) {
            System.out.println(time);
            List<Timestamp> ts = new ArrayList<>();
            int proche_der = superieur(plus_proche_derriere(deja_occupe_d, time), plus_proche_derriere(deja_occupe_f, time), plus_proche_derriere(libre_f, time));
            int proche_dev = inferieur(plus_proche_devant(deja_occupe_d, time), plus_proche_devant(deja_occupe_f, time), plus_proche_devant(libre_f, time));
            System.out.println(proche_der);
            System.out.println(proche_dev);
            if (plus_proche_derriere(deja_occupe_d, time) == null) {
                ts.add(time);
                if ((proche_dev == 3) || (plus_proche_devant(deja_occupe_f, time) == null)) {
                    ts.add(plus_proche_devant(libre_f, time));
                } else {
                    ts.add(plus_proche_devant(deja_occupe_d, time));
                }
                newList.add(ts);
            } else if ((proche_der != 1)) {
                ts.add(time);
                if (proche_dev == 3) {
                    ts.add(plus_proche_devant(libre_f, time));
                } else {
                    ts.add(plus_proche_devant(deja_occupe_d, time));
                }
                newList.add(ts);
            }
        }
        for (Timestamp time : libre_f) {
            List<Timestamp> ts = new ArrayList<>();
            int proche_der = superieur(plus_proche_derriere(deja_occupe_d, time), plus_proche_derriere(deja_occupe_f, time), plus_proche_derriere(libre_d, time));
            int proche_dev = inferieur(plus_proche_devant(deja_occupe_d, time), plus_proche_devant(deja_occupe_f, time), plus_proche_devant(libre_d, time));
            if (proche_der == 2) {
                ts.add(plus_proche_derriere(deja_occupe_f, time));
                ts.add(time);
                newList.add(ts);
            }
        }
        if (!newList.isEmpty()) {
            System.out.println("Salut");
            for (List<Timestamp> li : newList) {
                if (li.get(0).getTime() == li.get(1).getTime()) {
                    System.out.println("Coucou");
                    newList.remove(li);
                }
            }
        }
        return newList;
    }

    public static int superieur(Timestamp d_occ, Timestamp f_occ, Timestamp f) {
        if (d_occ == null) {
            d_occ = Timestamp.valueOf("2010-01-13 08:00:00");
        }
        if (f_occ == null) {
            f_occ = Timestamp.valueOf("2010-01-13 08:00:00");
        }
        if (f == null) {
            f = Timestamp.valueOf("2010-01-13 08:00:00");
        }

        if ((d_occ.getTime() >= f_occ.getTime()) && (d_occ.getTime() >= f.getTime())) {
            return 1;
        } else if ((f_occ.getTime() >= d_occ.getTime()) && (f_occ.getTime() >= f.getTime())) {
            return 2;
        } else {
            return 3;
        }
    }

    public static int inferieur(Timestamp d_occ, Timestamp f_occ, Timestamp f) {
        if (d_occ == null) {
            d_occ = Timestamp.valueOf("2010-01-13 08:00:00");
        }
        if (f_occ == null) {
            f_occ = Timestamp.valueOf("2010-01-13 08:00:00");
        }
        if (f == null) {
            f = Timestamp.valueOf("2010-01-13 08:00:00");
        }
        if ((d_occ.getTime() <= f_occ.getTime()) && (d_occ.getTime() <= f.getTime())) {
            return 1;
        } else if ((f_occ.getTime() <= d_occ.getTime()) && (f_occ.getTime() <= f.getTime())) {
            return 2;
        } else {
            return 3;
        }
    }

    public static Timestamp plus_proche_derriere(List<Timestamp> liste, Timestamp time) {
        if (!liste.isEmpty()) {
            Collections.sort(liste);
        }
        int i = 0;

        while ((i < liste.size()) && (liste.get(i).getTime()) < (time.getTime())) {
            i = i + 1;
        }

        if (i != 0) {
            return liste.get(i - 1);
        } else {
            return null;
        }
    }

    public static Timestamp plus_proche_devant(List<Timestamp> liste, Timestamp time) {
        Collections.sort(liste);
        int i = 0;
        while ((i < liste.size()) && (liste.get(i).getTime()) < (time.getTime())) {
            i = i + 1;
        }
        if (i < (liste.size())) {
            return liste.get(i);
        } else {
            return null;
        }
    }

    private static boolean plageAppartientListe(List<Timestamp> plage, List<List<Timestamp>> liste) {
        for (List<Timestamp> occ : liste) {
            Timestamp debut_occ = occ.get(0);
            Timestamp fin_occ = occ.get(1);

            if (!(fin_occ.before(plage.get(0)) || debut_occ.after(plage.get(1)))) {
                // La plage chevauche une plage occupée
                return true;
            }
        }
        return false;
    }
//

//    public static void main(String[] args) {
//        // Exemple d'utilisation
//        List<List<Timestamp>> disponibilites = new ArrayList<>();
//        List<List<Timestamp>> occupees = new ArrayList<>();
//
//        List<Timestamp> disponibilites_1 = new ArrayList<>();
//        List<Timestamp> occupees_1 = new ArrayList<>();
//        disponibilites_1.add(Timestamp.valueOf("2024-01-13 08:00:00"));
//        disponibilites_1.add(Timestamp.valueOf("2024-01-13 12:00:00"));
//        occupees_1.add(Timestamp.valueOf("2024-01-13 08:00:00"));
//        occupees_1.add(Timestamp.valueOf("2024-01-13 10:00:00"));
//
//        // Ajouter des plages de disponibilité et d'occupation
//        disponibilites.add(disponibilites_1);
//
//        occupees.add(occupees_1);
//
//        // Appeler la méthode pour obtenir les disponibilités réelles
//        List<List<Timestamp>> disponibilitesReelles = Verif_dispo(disponibilites, occupees);
//
//        // Afficher les résultats
//        for (List<Timestamp> dispo : disponibilitesReelles) {
//            System.out.println("Disponibilité : " + dispo.get(0) + " à " + dispo.get(1));
//        }
//    }
//}
    public static void main(String[] args) throws SQLException {
        try {
            Connection con = connectSurServeurM3();
            List<operation_effectuee> liset_c = Meilleurs_operation_produit(con, get_ex(con, 1));
            System.out.println(liset_c + "Salut");
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            System.out.println(timestamp.getTime());
//            List<Timestamp> liste_tst = Premiere_dispo(timestamp, 50, 3, 1, con);
//            System.out.println("liste des états :");
//            System.out.println(liste_tst);
//           rOperateur.saveInDBV(connectSurServeurM3());
        } catch (SQLException ex) {
            throw new Error(ex);
        }
    }

}
