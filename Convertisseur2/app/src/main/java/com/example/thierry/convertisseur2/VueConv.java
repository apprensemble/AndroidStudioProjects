package com.example.thierry.convertisseur2;

import android.app.Activity;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Created by thierry on 16/03/16.
 */
public class VueConv {
    private Activity monActivite;
    private ControllerConv monController;

    private EditText celcius, farenheit, kelvin;
    private TextView message;
    //devrait etre dans le model
    private DecimalFormat deuxDigit;

    public VueConv(Activity monActivite) {
        this.monActivite = monActivite;
        celcius = (EditText) monActivite.findViewById(R.id.eCelcius);
        farenheit = (EditText) monActivite.findViewById(R.id.eFarenheit);
        kelvin = (EditText) monActivite.findViewById(R.id.eKelvin);
        message = (TextView) monActivite.findViewById(R.id.message);
    }

    public void setMonController(ControllerConv monController) {
        this.monController = monController;
    }

    private View.OnTouchListener neFaisRien;

    private void activeTouchListener() {
        neFaisRien = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText et = (EditText) v;
                et.setSelection(et.getOffsetForPosition(event.getX(), event.getY()));
                v.requestFocus();
                return true;
            }
        };

        kelvin.setOnTouchListener(neFaisRien);
        farenheit.setOnTouchListener(neFaisRien);
        celcius.setOnTouchListener(neFaisRien);
    }

    private void activeRaz() {
        final TextView.OnEditorActionListener actionRAZ = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_NULL == actionId) {
                    resetEcran();
                    return true;
                }
                return false;
            }
        };

        celcius.setOnEditorActionListener(actionRAZ);
        farenheit.setOnEditorActionListener(actionRAZ);
        kelvin.setOnEditorActionListener(actionRAZ);

    }

    private void resetEcran() {
            kelvin.setText("");
            farenheit.setText("");
            celcius.setText("");
        monController.resetEcran();
    }

    public void kelvinValide(Boolean etat) {
        if(etat) {
            kelvin.setTextColor(Color.BLACK);
            celcius.setTextColor(Color.BLACK);
            farenheit.setTextColor(Color.BLACK);
            message.setText("");
        }
        else {
            kelvin.setTextColor(Color.RED);
            celcius.setTextColor(Color.RED);
            farenheit.setTextColor(Color.RED);
            message.setText(R.string.infZeroAbs);
            //vibreur.vibrate(vibAlerte,-1);
        }
    }

    public void affiche(EditText editText, Double v) {
        editText.setText(deuxDigit.format(v));
    }



    public void convertirDepuisKelvin(CharSequence s) {
        monController.convertirDepuisKelvin(s);

    }

    public void afficheCelcius(Double vCelcius) {
        affiche(celcius, vCelcius);
    }

    public void afficheFarenheit(Double vFarenheit) {
        affiche(farenheit, vFarenheit);
    }

    public void afficheKelvin(Double vKelvin) {
        affiche(kelvin, vKelvin);
    }
}
