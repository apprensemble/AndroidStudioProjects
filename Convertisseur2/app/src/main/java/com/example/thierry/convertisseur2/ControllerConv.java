package com.example.thierry.convertisseur2;

import android.app.Activity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by thierry on 16/03/16.
 */
public class ControllerConv {

    private final VueConv maVue;
    private final ModelConv monModel;
    private final DecimalFormat deuxDigit;

    public ControllerConv(VueConv maVue, ModelConv monModel) {
        this.maVue = maVue;
        this.monModel = monModel;
        deuxDigit = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
    }

    public void resetEcran() {
        monModel.resetEcran();
    }

    private Boolean entreeValide(CharSequence s) {
        return s.toString().matches("[-]?[0-9][0-9.]*");
    }

    public void convertirDepuisKelvin(CharSequence s) {
        if (entreeValide(s)) {
            Map<String,Double> res = monModel.convertirDepuisKelvin(Double.valueOf(s.toString()));
            maVue.afficheCelcius(res.get("celcius"));
            maVue.afficheFarenheit(res.get("farenheit"));
        }
    }

    public void convertirDepuisCelcius(CharSequence s) {
        if (entreeValide(s)) {
            Map<String,Double> res = monModel.convertirDepuisCelcius(Double.valueOf(s.toString()));
            maVue.afficheKelvin(res.get("kelvin"));
            maVue.afficheFarenheit(res.get("farenheit"));
        }
    }
    public void convertirDepuisFarenheit(CharSequence s) {
        if (entreeValide(s)) {
            Map<String, Double> res = monModel.convertirDepuisFarenheit(Double.valueOf(s.toString()));
            maVue.afficheCelcius(res.get("celcius"));
            maVue.afficheKelvin(res.get("kelvin"));
        }
    }

}
