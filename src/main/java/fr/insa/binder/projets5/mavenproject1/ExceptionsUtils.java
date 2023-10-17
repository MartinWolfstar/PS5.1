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
package fr.insa.binder.projets5.mavenproject1;

/**
 *
 * @author francois
 */
public class ExceptionsUtils {
    
    public static String messageEtPremiersAppelsDansPackage(Throwable ex,String packageName,int nbrAppels) {
        String res = "--> (display only first " + nbrAppels + " in package : " + packageName + ")\n" + 
                "Exception in thread \"" + Thread.currentThread().getName() +
                "\" " + ex.getClass().getName() +
                ": " + ex.getLocalizedMessage();
        int i = 0;
        int nbr = 0;
        StackTraceElement[] elems = ex.getStackTrace();
        while (nbr < nbrAppels && i < elems.length) {
            if (elems[i].getClassName().startsWith(packageName)) {
//                res = res + "\n" + i + ": at " + elems[i].getClassName() +
                res = res + "\n    at " + elems[i].getClassName() +
                        "." + elems[i].getMethodName() + "(" + 
                        elems[i].getFileName() + ":" +elems[i].getLineNumber() + ")";
                nbr ++;
            }
            i ++;
        }
        return res;
    }
    
}
