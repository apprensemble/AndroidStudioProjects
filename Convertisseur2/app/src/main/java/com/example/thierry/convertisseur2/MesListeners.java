package com.example.thierry.convertisseur2;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by thierry on 16/03/16.
 */
public class MesListeners implements TextWatcher{
    private EditText editText;
    private Boolean kelvin;
    private VueConv maVue;
    public MesListeners(View v, Boolean kelvin) {
        editText = (EditText) v;
        this.kelvin = kelvin;
    }

    public void setVue(VueConv maVue) {
        this.maVue = maVue;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (editText.hasFocus()) {
            convertirDepuisKelvin(s);
        }
    }

    private void convertirDepuisKelvin(CharSequence s) {
        maVue.convertirDepuisKelvin(s);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            if (kelvin) maVue.kelvinValide(Double.valueOf(s.toString()) >= 0);
        }
        catch (NullPointerException e) {

        }

    }
}
