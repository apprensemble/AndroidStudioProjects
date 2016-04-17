package com.example.thierry.todolist;

/**
 * Created by thierry on 17/04/16.
 */
public class Espaceur {

    public static String espace(int nbrEspace) {
        StringBuilder espace = new StringBuilder("");
        nbrEspace *= 2;
        nbrEspace -= 1;
        for (int i = 0; i < nbrEspace; i++) {
            espace.append(" ");
        }
        return espace.toString();
    }

    public static String diffText (String text1, String text2) {
        int t1 = text1.length();
        int t2 = text2.length();
        return t1 > t2 ? espace(0) : espace(t2 - t1);
    }
}
