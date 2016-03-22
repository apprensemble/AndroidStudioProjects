package com.example.thierry.convertisseur2;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by thierry on 16/03/16.
 */
public class MesListeners implements TextWatcher{
    private final EditText editText;
    private VueConv maVue;
    public MesListeners(View v) {
        editText = (EditText) v;
    }

    public void setMaVue(VueConv maVue) {
        this.maVue = maVue;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    private void convertirDepuisKelvin(CharSequence s) {
        maVue.convertirDepuisKelvin(s);
    }

    private void convertirDepuisFarenheit(CharSequence s) {
        maVue.convertirDepuisFarenheit(s);
    }

    private void convertirDepuisCelcius(CharSequence s) {
        maVue.convertirDepuisCelcius(s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (editText.hasFocus()&&entreeValide(s)) {
            convertirDepuisKelvin(s);
        }
    }

    private Boolean entreeValide(CharSequence s) {
        return s.toString().matches("[-]?[0-9][0-9.]*");
    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            if (entreeValide(editText.getText())) maVue.kelvinValide(Double.valueOf(s.toString()) >= 0);
        }
        catch (NullPointerException e) {

        }

    }
}
