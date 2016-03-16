package com.example.thierry.convertisseur2;

/**
 * Created by thierry on 16/03/16.
 */
public class Convertisseur {
    public static Double c2f(Double s) {
        s = (s * 9/5 + 32);
        return s;
    }

    public static Double f2k(Double s) {
        s = (s + 459.67) * 5/9;
        return s;
    }

    public static Double k2c(Double s) {
        s = (s - 273.15);
        return s;
    }
}
