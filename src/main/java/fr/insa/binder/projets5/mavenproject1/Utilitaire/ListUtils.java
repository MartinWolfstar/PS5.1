/*
    Copyright 2000-2014 Francois de Bertrand de Beuvron

    This file is part of UtilsBeuvron.

    UtilsBeuvron is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UtilsBeuvron is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UtilsBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.binder.projets5.mavenproject1.Utilitaire;

import fr.insa.binder.projets5.mavenproject1.Utilitaire.ConsoleFdB;
import fr.insa.binder.projets5.mavenproject1.Utilitaire.StringUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author francois
 */
public class ListUtils {

    /**
     * Indique simplement comment les éléments sont affichés dans la liste.
     *
     * @param <E>
     */
    @FunctionalInterface
    public interface ElemFormatter<E> {

        public String format(E elem);
    }

    public static String formatList(List<? extends Object> list,
            String debut, String fin, String separateur) {
        return formatList(list, debut, fin, separateur, Object::toString);
    }

    /**
     * formatte une liste.
     *
     * @param <E>
     * @param list
     * @param debut ajouté au début de la liste
     * @param fin ajouté à la fin de la liste
     * @param separateur ajouté entre chaque élément
     * @param formatter pour formatter chaque élément
     * @return représentation formattée de la liste
     */
    public static <E> String formatList(List<? extends E> list,
            String debut, String fin, String separateur,
            ElemFormatter<E> formatter) {
        StringBuilder res = new StringBuilder();
        res.append(debut);
        for (int i = 0; i < list.size(); i++) {
            res.append(formatter.format(list.get(i)));
            if (i != list.size() - 1) {
                res.append(separateur);
            }
        }
        res.append(fin);
        return res.toString();
    }

    /**
     * énumère les éléments dans une liste.
     *
     * @param <E>
     * @param list
     * @param beforeNum ajouté avant le numéro pour chaque élément
     * @param debutNumerotation la numérotation commence à cette valeur
     * @param betweenNumAndVal ajouté entre le numéro et l'élément
     * @param afterVal ajouté après chaque élément
     * @param formatter pour formatter chaque élément
     * @return représentation formattée sous forme d'énumération de la liste
     */
    public static <E> String enumerateList(List<? extends E> list,
            String beforeNum, int debutNumerotation, String betweenNumAndVal, String afterVal,
            ElemFormatter<? super E> formatter) {
        StringBuilder res = new StringBuilder();
        if (list.size() != 0) {
            int nbrDigit = (int) Math.floor(Math.log10(list.size())) + 1;
            String beforeOtherLines = StringUtil.mult(" ", beforeNum.length() + nbrDigit) + betweenNumAndVal;
            for (int i = 0; i < list.size(); i++) {
                String beforeFirstLine = beforeNum
                        + String.format("%" + nbrDigit + "d", (i + debutNumerotation))
                        + betweenNumAndVal;
                res.append(StringUtil.specialIndent(formatter.format(list.get(i)), beforeFirstLine, beforeOtherLines));
                res.append(afterVal);
            }
        }
        return res.toString();
    }

    /**
     * énumère les éléments dans une liste.
     *
     * @return
     * {@code enumerateList(list, "", 1, " : ", "\n", Object::toString);}
     */
    public static String enumerateList(List<? extends Object> list) {
        return enumerateList(list, "", 1, " : ", "\n", Object::toString);
    }

    /**
     * énumère les éléments dans une liste.
     *
     * @return {@code enumerateList(list, "", 1, " : ", "\n", formatter);}
     */
    public static <E> String enumerateList(List<? extends E> list, ElemFormatter<E> formatter) {
        return enumerateList(list, "", 1, " : ", "\n", formatter);
    }

    public static <E> E selectOne(String titre, List<E> list, ElemFormatter<? super E> formatter) {
        int rep = -1;
        while (rep <= 0 || rep > list.size()) {
            System.out.println(titre);
            System.out.println(ListUtils.enumerateList(list, "  ", 1, " : ", "\n", formatter));
            rep = ConsoleFdB.entreeInt("votre choix : ");
        }
        return list.get(rep - 1);
    }

    public static <E> Optional<E> selectOneOrCancel(String titre, List<E> list, ElemFormatter<? super E> formatter) {
        int rep = -1;
        while (rep < 0 || rep > list.size()) {
            System.out.println(titre);
            System.out.println(ListUtils.enumerateList(list, "  ", 1, " : ", "\n", formatter));
            rep = ConsoleFdB.entreeInt("votre choix (0 pour annuler) : ");
        }
        if (rep == 0) {
            return Optional.empty();
        } else {
            return Optional.of(list.get(rep - 1));
        }
    }

    public static <E> List<E> selectMultiple(String titre,
            List<E> selectionnes, List<E> selectionnables,
            ElemFormatter<? super E> formatter) {
        int rep = -1;
        List<E> selected = new LinkedList<>(selectionnes);
        List<E> notSelected = new LinkedList<>(selectionnables);
        while (rep != 0) {
            System.out.println(titre);
            System.out.println("-- Actuellement selectionnés ==> déselectionnables :");
            if (selected.isEmpty()) {
                System.out.println("AUCUN");
            } else {
                System.out.println(ListUtils.enumerateList(selected, "  ", 1, " : ", "\n", formatter));
            }
            System.out.println("-- selectionnables :");
            if (notSelected.isEmpty()) {
                System.out.println("PLUS AUCUN");
            } else {
                System.out.println(ListUtils.enumerateList(notSelected, "  ", selected.size() + 1, " : ", "\n", formatter));
            }
            rep = ConsoleFdB.entreeInt("votre choix (0 pour finir) : ");
            if (rep > 0 && rep <= selected.size()) {
                E elem = selected.get(rep-1);
                selected.remove(rep-1);
                notSelected.add(elem);
            } else if (rep > selected.size() && rep <= selected.size()+notSelected.size()) {
                int index = rep - selected.size() - 1;
                E elem = notSelected.get(index);
                notSelected.remove(index);
                selected.add(elem);
            }
        }
        return new ArrayList<>(selected);
    }
    
    public static <E> List<E> selectMultiple(String titre,
            List<E> selectionnables,
            ElemFormatter<? super E> formatter) {
        return selectMultiple(titre, new LinkedList<E>(), selectionnables, formatter);
    }
    
    public static void testSelMult() {
        List<String> select = selectMultiple("-- selectionnez ", 
                Stream.iterate(1, i -> i < 10, i -> i+1)
                .map(i -> String.format("test%2d", i)).toList(),
                String::toString);
        System.out.println("Vous avez selectionné : ");
        System.out.println(enumerateList(select));
    }
    
    public static void main(String[] args) {
        testSelMult();
    }

}
