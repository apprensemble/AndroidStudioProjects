package com.example.thierry.convertisseur2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.getInteger;

public class MainActivity extends AppCompatActivity {

    private EditText celcius, farenheit, kelvin;
    private Double vFarenheit, vCelcius, vKelvin;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        celcius = (EditText) findViewById(R.id.eCelcius);
        farenheit = (EditText) findViewById(R.id.eFarenheit);
        kelvin = (EditText) findViewById(R.id.eKelvin);
        message = (TextView) findViewById(R.id.message);

        TextView.OnEditorActionListener actionRAZ = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                resetEcran();
                return true;
            }
        };
        celcius.setOnEditorActionListener(actionRAZ);
        farenheit.setOnEditorActionListener(actionRAZ);
        kelvin.setOnEditorActionListener(actionRAZ);

        kelvin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (kelvin.hasFocus()) {
                    convertirDepuisKelvin(s);
                }
                if (vKelvin <0) kelvinValide(false);
                else kelvinValide(true);

            }
        });
        celcius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (celcius.hasFocus()) {
                    convertirDepuisCelcius(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        farenheit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (farenheit.hasFocus()) {
                    convertirDepuisFarenheit(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Boolean entreeValide(CharSequence s) {
        if (s.toString().matches("[-]?[0-9][0-9.]*")) {
            return true;
        }
        return false;
    }

    private void kelvinValide(Boolean etat) {
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
        }
    }




    public void convertirDepuisFarenheit(CharSequence s) {
        if (entreeValide(s)) {
            vFarenheit = Double.valueOf(s.toString());
            vKelvin = f2k(vFarenheit);
            vCelcius = k2c(vKelvin);
            affiche(kelvin, vKelvin);
            affiche(celcius,vCelcius);
        }
    }

    public void convertirDepuisCelcius(CharSequence s) {
        if (entreeValide(s)) {
            vCelcius = Double.valueOf(s.toString());
            vFarenheit = c2f(vCelcius);
            vKelvin = f2k(vFarenheit);
            affiche(farenheit,vFarenheit);
            affiche(kelvin,vKelvin);
        }

    }

    private void resetEcran() {
        kelvin.setText("");
        farenheit.setText("");
        celcius.setText("");
    }

    public void convertirDepuisKelvin(CharSequence s) {
        if (entreeValide(s)) {
            vKelvin = Double.valueOf(s.toString());
            vCelcius = k2c(vKelvin);
            vFarenheit = c2f(vCelcius);
            affiche(celcius,vCelcius);
            affiche(farenheit,vFarenheit);
        }
    }

    public void affiche(EditText editText, Double v) {
        editText.setText(v.toString());
    }

    private Double c2f(Double s) {
        s = (s * 9/5 + 32);
        return s;
    }

    private Double f2k(Double s) {
        s = (s + 459.67) * 5/9;
        return s;
    }

    private Double k2c(Double s) {
        s = (s - 273.15);
        return s;
    }
}
