package com.example.thierry.convertisseur2;

import java.util.Map;

/**
 * Created by thierry on 16/03/16.
 */
public class ControllerConv {

    private final VueConv maVue;
    private final ModelConv monModel;

    public ControllerConv(VueConv maVue, ModelConv monModel) {
        this.maVue = maVue;
        this.monModel = monModel;
    }

    public void resetEcran() {
        monModel.RAZ();
    }

    public void convertirDepuisKelvin(CharSequence s) {
            Map<String,Double> res = monModel.convertirDepuisKelvin(Double.valueOf(s.toString()));
            maVue.afficheCelcius(res.get("celcius"));
            maVue.afficheFarenheit(res.get("farenheit"));
    }

    public void convertirDepuisCelcius(CharSequence s) {
            Map<String,Double> res = monModel.convertirDepuisCelcius(Double.valueOf(s.toString()));
            maVue.afficheKelvin(res.get("kelvin"));
            maVue.afficheFarenheit(res.get("farenheit"));
    }
    public void convertirDepuisFarenheit(CharSequence s) {
            Map<String, Double> res = monModel.convertirDepuisFarenheit(Double.valueOf(s.toString()));
            maVue.afficheCelcius(res.get("celcius"));
            maVue.afficheKelvin(res.get("kelvin"));
    }

}
