/*
    Copyright 2000-2011 Francois de Bertrand de Beuvron

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtil implements java.io.Serializable {

    static final long serialVersionUID = 30101L;

    public static String valueOf(int[] tabInt) {
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < tabInt.length; i++) {
            res.append(tabInt[i]);
            if (i < tabInt.length - 1) {
                res.append(" ");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     *
     * @return true iff the string is a standard java identifier : ie begin
     * whith a {@link java.lang.Character#isJavaIdentifierStart(char ch)} and
     * continue whith {@link java.lang.Character#isJavaIdentifierPart(char ch)}
     */
    public static boolean isSimpleSymbol(String s) {
        boolean res = s.length() > 0 && Character.isJavaIdentifierStart(s.charAt(0));
        int i = 1;
        while (res && i < s.length()) {
            res = Character.isJavaIdentifierPart(s.charAt(i));
            i++;
        }
        return res;
    }

    /**
     * write a symbol without " if it is a valid Java identifier, with " aroud,
     * and with rewriting of the escape characters
     *
     * @param s the initial string
     * @return s written as a canonical symbol
     */
    public static String getCanonicalSymbol(String s) {
        if (isSimpleSymbol(s)) {
            return s;
        } else {
            StringBuilder res = new StringBuilder("\"");
            for (int i = 0; i < s.length(); i++) {
                char cur = s.charAt(i);
                if (cur == '\n') {
                    res.append("\\n");
                } else if (cur == '\r') {
                    res.append("\\r");
                } else if (cur == '\t') {
                    res.append("\\t");
                } else if (cur == '\b') {
                    res.append("\\b");
                } else if (cur == '\f') {
                    res.append("\\f");
                } else if (cur == '"') {
                    res.append("\\\"");
                } else if (cur == '\'') {
                    res.append("\\\'");
                } else if (cur == '\\') {
                    res.append("\\\\");
                } else if (cur == ' ') {
                    res.append(" ");
                } else if (Character.isJavaIdentifierPart(cur)) {
                    res.append(cur);
                } else {
                    res.append("\\u");
                    String code = "0000" + Integer.toHexString((int) cur);
                    code = code.substring(code.length() - 4);
                    res.append(code);
                }
            }
            res.append("\"");
            return res.toString();
        }
    }

    /**
     * parse a string as if by the java compiler.
     *
     * @param s the initial string
     * @return s with all escape characters interpreted
     */
    public static String parse(String s) throws StringFormatException {
        if (s.length() < 2 || s.charAt(0) != '"' || s.charAt(s.length() - 1) != '"') {
            throw new StringFormatException("should begin and end with \"");
        }
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            char cur = s.charAt(i);
            if (cur != '\\') {
                res.append(cur);
            } else {
                i++;
                if (i >= s.length()) {
                    throw new StringFormatException("invalid escape sequence \\ alone");
                }
                cur = s.charAt(i);
                char pred = cur;
                if (cur == 'n') {
                    res.append("\n");
                } else if (cur == 'r') {
                    res.append("\r");
                } else if (cur == 't') {
                    res.append("\t");
                } else if (cur == 'b') {
                    res.append("\b");
                } else if (cur == 'f') {
                    res.append("\f");
                } else if (cur == '"') {
                    res.append("\"");
                } else if (cur == '\'') {
                    res.append("\'");
                } else if (cur == '\\') {
                    res.append("\\");
                } else if (cur == 'u') {
                    while (i < s.length() && s.charAt(i) == 'u') {
                        i++;
                    }
                    if (i + 3 >= s.length()) {
                        throw new StringFormatException("invalid escape sequence \\u without 4 hex digits");
                    }
                    int hexVal = 0;
                    try {
                        hexVal = Integer.parseInt(s.substring(i, i + 4), 16);
                    } catch (NumberFormatException e) {
                        throw new StringFormatException("invalid escape sequence invalid hex digits after \\u");
                    }
                    i = i + 3;
                    res.append((char) hexVal);
                } else if (cur >= '0' && cur <= '3') {
                    int octalVal = (int) cur;
                    i++;
                    if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '7') {
                        octalVal = octalVal * 8 + ((int) s.charAt(i));
                        i++;
                        if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '7') {
                            octalVal = octalVal * 8 + ((int) s.charAt(i));
                        }
                    }
                    res.append((char) octalVal);
                } else if (cur >= '4' && cur <= '7') {
                    int octalVal = (int) cur;
                    i++;
                    if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '7') {
                        octalVal = octalVal * 8 + ((int) s.charAt(i));
                        i++;
                    }
                    res.append((char) octalVal);
                } else {
                    throw new StringFormatException("invalid escape sequence \\" + pred);
                }
            }
        }
        return res.toString();
    }

    /**
     * the concatenation of s with itself nbg times.
     *
     * @param s
     * @param nbr
     * @return the concatenation of s with itself nbg times.
     */
    public static String mult(String s, int nbr) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nbr; i++) {
            res.append(s);
        }
        return res.toString();
    }

    public static String padLeft(String s, int largeur) {
        return mult(" ", largeur - s.length()) + s;
    }

    public static String padRight(String s, int largeur) {
        return s + mult(" ", largeur - s.length());
    }

    /**
     * put beforeFirstLine at the begining (first line) of s, put
     * beforeOtherLines at the begining of the other lines.
     */
    public static String specialIndent(String s, String beforeFirstLine, String beforeOtherLines) {
        StringBuilder res = new StringBuilder();
        res.append(beforeFirstLine);
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            res.append(cur);
            if (cur == '\n') {
                res.append(beforeOtherLines);
            }
        }
        return res.toString();
    }

    /**
     * put before at the begining of each line in s
     *
     * @param s
     * @param before
     * @return indented s
     */
    public static String specialIndent(String s, String before) {
        return specialIndent(s, before, before);
    }

    /**
     * add spaces before each line in s
     *
     * @param s the original string
     * @param nbrSpace the number of space to insert at the begining of each
     * line in s
     * @return indented s
     */
    public static String indent(String s, int nbrSpace) {
        String spaces = mult(" ", nbrSpace);
        return specialIndent(s, spaces);
    }

    public static String coupeLonguesLignes(String text, int maxCharParLigne) {
        try {
            BufferedReader bin = new BufferedReader(new StringReader(text));
            StringBuilder res = new StringBuilder(text.length());
            String line;
            while ((line = bin.readLine()) != null) {
                while (line.length() > maxCharParLigne) {
                    res.append(line.substring(0, maxCharParLigne));
                    res.append('\n');
                    line = line.substring(maxCharParLigne);
                }
                res.append(line);
                res.append('\n');
            }
            return res.toString();
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }

    //==================================== some utilities
    /**
     * replace <return> ("\n") with " <BR>\n" in string (to produce HTML)
     */
    public static String replaceReturnBR(String in) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) == '\n') {
                res.append(" <BR>\n");
            } else {
                res.append(in.charAt(i));
            }
        }
        return res.toString();
    }

    /**
     * add " and + that allow to define a text as a string in a java source
     * file. Attention : ne fonctionne pas bien pour la dernière ligne du texte.
     */
    public static String fromTextToJavaSourceString(String text) {
        String res = text;
        res = res.replaceAll(Pattern.quote("\\"), Matcher.quoteReplacement("\\\\"));
        res = res.replaceAll(Pattern.quote("\""), Matcher.quoteReplacement("\\\""));
        StringBuilder res2 = new StringBuilder(res.length());
        res2.append('\"');
        for (int i = 0; i < res.length(); i++) {
            char c = res.charAt(i);
            if (c == '\n') {
                res2.append("\\n\"+\n\"");
            } else {
                res2.append(c);
            }
        }
        res2.append('\"');
        return res2.toString();
    }

    /**
     * "escape" les caractères sep et esc dans chacun des items, puis
     * concatène les items en les séparant par sep.
     * <pre>
     * le but est de garantir que pour toute liste de chaines items, on ait :
     * decodeEscapedCSV(encodeEscapedCSV(items,sep,esc),sep,esc) = items
     * et pour toute chaine csv on ait :
     * encodeEscapedCSV(decodeEscapedCSV(csv,sep,esc),sep,esc) = csv
     * On voit facilement le problème sur un exemple :
     * supposons esc = '/' et sep = ','
     * si items = ["coucou","toto"]
     * pas de problème : il suffit de concaténer en separant par , :
     * ["coucou","toto"] --encode--> "coucou,toto"
     * Et pour décoder, il suffirait de faire un split sur ","
     * "coucou,toto".split(",") --> ["coucou","toto"]  OK (en transformant le tableau en liste)
     * Le problème apparait si un des items contient le séparateur :
     * exemple items = ["pi","3,14"]
     * je risque d'avoir 
     * ["pi","3,14"] --encode--> "pi,3,14" --decode--> ["pi","3","14"]  BAD
     * Pour éviter cela on va utiliser le caractère esc pour précéder les séparateurs dans les items :
     * ["pi","3,14"] --encode--> "pi,3/,14" --decode--> ["pi","3,14"] 
     * cas particulier du cas particulier : j'ai / en fin d'item :
     * ["bi,zarre"] --encode--> "bi/,zarre"     |
     * ["bi/","zarre"] --encode--> "bi/,zarre"  | AMBIGU
     * il faut donc également faire un "escape" du / : / --> //
     * ["bi,zarre"] --encode--> "bi/,zarre"      |
     * ["bi/","zarre"] --encode--> "bi//,zarre"  | NON AMBIGU
     * 
     * Le codage consiste donc à remplacer d'abord tous les / par // puis
     * toutes les , par /,
     * </pre>
     *
     * @param items
     * @param sep
     * @param esc
     * @return
     */
    public static String encodeEscapedCSV(List<String> items, char sep, char esc) {
        String sEscape = "" + esc;
        String repEscape = sEscape + sEscape;
        String sSeparator = "" + sep;
        String repSeparator = sEscape + sSeparator;
        return items.stream()
                .map((t) -> t.replace(sEscape, repEscape).replace(sSeparator, repSeparator))
                .collect(Collectors.joining(sSeparator));
    }

    /**
     * decode une chaîne encodé par {@link #encodeEscapedCSV(java.util.List, char, char)}.
     * Voir explication générale dans {@link #encodeEscapedCSV(java.util.List, char, char)}.
     * <pre>
     * Le décodage se fait suivant deux modes avec l'algo suivant :
     * . modeEscape = false
     * . res = []
     * . curItem = ""
     * . pour chaque caractere cur de line (lus du début à la fin)
     *   . si modeEscape
     *     . modeEscape = false
     *     . si cur == sep || cur == esc
     *         . curItem = curItem + cur
     *       sinon
     *         ---> erreur
     *     sinon
     *     . si cur == esc
     *         . modeEscape = true
     *       sinon si cur != sep
     *         . curItem = curItem + cur
     *       sinon
     *         . res.add(curItem)
     *         . curItem = ""
     * . res.add(curItem)   // ne pas oublier le dernier non fini par le séparateur
     * . return res
     * </pre>
     * @param line
     * @param sep
     * @param esc
     * @return
     * @throws StringFormatException 
     */
    public static List<String> decodeEscapedCSV(String line, char sep, char esc) throws StringFormatException {
        List<String> res = new ArrayList<>();
        boolean modeEscape = false;
        StringBuilder curItem = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char cur = line.charAt(i);
            if (modeEscape) {
                if (cur == sep || cur == esc) {
                    curItem.append(cur);
                } else {
                    throw new StringFormatException("Escape error : " + esc + cur + " est illégal dans \"" + line + "\"");
                }
                modeEscape = false;
            } else {
                if (cur == esc) {
                    modeEscape = true;
                } else if (cur == sep) {
                    res.add(curItem.toString());
                    curItem = new StringBuilder();
                } else {
                    curItem.append(cur);
                }
            }
        }
        res.add(curItem.toString());
        return res;
    }

}
