package com.example.thierry.convertisseur2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private Vibrator vibreur;
    final private long [] vibAlerte = {0,50,100,100};


    public VueConv(Activity monActivite) {
        this.monActivite = monActivite;
        celcius = (EditText) monActivite.findViewById(R.id.eCelcius);
        farenheit = (EditText) monActivite.findViewById(R.id.eFarenheit);
        kelvin = (EditText) monActivite.findViewById(R.id.eKelvin);
        message = (TextView) monActivite.findViewById(R.id.message);
        deuxDigit = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        vibreur = (Vibrator) monActivite.getSystemService(monActivite.VIBRATOR_SERVICE);

        celcius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (celcius.isFocused()&&entreeValide(s)) {
                    s = monController.valideEntree(s);
                    convertirDepuisCelcius(s);
                }
                else if(celcius.isFocused() && start==0 && before==1 && count==0){
                    resetEcran();
                }
//                Log.i("start - before - count",s+" "+start+" "+before+" "+count);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        kelvin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (kelvin.isFocused()&&entreeValide(s)) {
                    s = monController.valideEntree(s);
                    convertirDepuisKelvin(s);
                }
                else if(kelvin.isFocused() && start==0 && before==1 && count==0){
                    resetEcran();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    CharSequence seq = kelvin.getText();
                    seq = monController.valideEntree(seq);
                    if (entreeValide(seq)) kelvinValide(Double.valueOf(s.toString()) >= 0);
                }
                catch (NullPointerException e) {

                }
            }
        });
        farenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (farenheit.hasFocus()&&entreeValide(s)) {
                    s = monController.valideEntree(s);
                    convertirDepuisFarenheit(s);
                }
                else if(farenheit.isFocused() && start==0 && before==1 && count==0){
                    resetEcran();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        activeRaz();
        activeTouchListener();
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
            vibreur.vibrate(vibAlerte,-1);
        }
    }

    public void affiche(EditText editText, Double v) {
        editText.setText(deuxDigit.format(v));
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

    private Boolean entreeValide(CharSequence s) {
        return s.toString().matches("([-]?[.0-9][0-9]*)|([-]?[0-9][.0-9]*)");
    }

    public void convertirDepuisKelvin(CharSequence s) {
        monController.convertirDepuisKelvin(s);
    }

    public void convertirDepuisCelcius(CharSequence s) {
        monController.convertirDepuisCelcius(s);
    }

    public void convertirDepuisFarenheit(CharSequence s) {
        monController.convertirDepuisFarenheit(s);
    }
}
