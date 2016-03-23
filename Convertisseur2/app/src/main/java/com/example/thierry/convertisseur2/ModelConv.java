package com.example.thierry.convertisseur2;

import java.util.HashMap;
import java.util.Map;

import static com.example.thierry.convertisseur2.Convertisseur.c2f;
import static com.example.thierry.convertisseur2.Convertisseur.f2k;
import static com.example.thierry.convertisseur2.Convertisseur.k2c;

/**
 * Created by thierry on 16/03/16.
 */
public class ModelConv {
    private Double vFarenheit, vCelcius, vKelvin;

    public ModelConv() {
        vCelcius = new Double(0);
        vFarenheit = new Double(c2f(vCelcius));
        vKelvin = new Double(f2k(vFarenheit));
    }

    public void RAZ() {
        vKelvin = 0.0;
    }

    public Map<String, Double> convertirDepuisKelvin(Double aDouble) {
        vKelvin = aDouble;
        vCelcius = k2c(vKelvin);
        vFarenheit = c2f(vCelcius);
        Map<String,Double> resultat = new HashMap<>();
        resultat.put("farenheit",vFarenheit);
        resultat.put("celcius",vCelcius);
        return resultat;
    }

    public Map<String, Double> convertirDepuisCelcius(Double aDouble) {
        vCelcius = aDouble;
        vFarenheit = c2f(vCelcius);
        vKelvin = f2k(vFarenheit);
        Map<String,Double> resultat = new HashMap<>();
        resultat.put("farenheit",vFarenheit);
        resultat.put("kelvin",vKelvin);
        return resultat;
    }

    public Map<String, Double> convertirDepuisFarenheit(Double aDouble) {
        vFarenheit = aDouble;
        vKelvin = f2k(vFarenheit);
        vCelcius = k2c(vKelvin);
        Map<String,Double> resultat = new HashMap<>();
        resultat.put("celcius",vCelcius);
        resultat.put("kelvin",vKelvin);
        return resultat;
    }


}
