package com.example.thierry.convertisseur2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.getInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText celcius = (EditText) findViewById(R.id.eCelcius);
        EditText farenheit = (EditText) findViewById(R.id.eFarenheit);
        EditText kelvin = (EditText) findViewById(R.id.eKelvin);

        kelvin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (hasWindowFocus()) {
                    afficheK(s);
                }

            }
        });
        celcius.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (hasWindowFocus()) {
                    afficheC(s);
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
                if (hasWindowFocus()) {
                    afficheF(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
/*
        kelvin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                afficheK(((EditText) findViewById(R.id.eKelvin)).getText());
            }
        });
        */
    }

    public void convertirDepuisCelcius(CharSequence s) {
        EditText celcius = (EditText) findViewById(R.id.eCelcius);
        EditText kelvin = (EditText) findViewById(R.id.eKelvin);
        EditText farenheit = (EditText) findViewById(R.id.eFarenheit);
        Double vFarenheit, vCelcius, vKelvin;
        if (s.toString().matches("[-]?[0-9][0-9.]*")) {
            if (farenheit.hasFocus()) {
                vFarenheit = Double.valueOf(s.toString());
            }
            else
            else if (kelvin.hasFocus()) {

            }
        }

    }

    private void afficheF(CharSequence s) {
        EditText farenheit = (EditText) findViewById(R.id.eFarenheit);
        if (farenheit.hasFocus() && s.toString().matches("[-]?[0-9][0-9.]*")) {
            Double vFarenheit = Double.valueOf(s.toString());

            EditText celcius = (EditText) findViewById(R.id.eCelcius);
            EditText kelvin = (EditText) findViewById(R.id.eKelvin);
            Double vKelvin = f2k(vFarenheit);
            Double vCelcius = k2c(vKelvin);
            celcius.setText(vCelcius.toString());
            kelvin.setText(vKelvin.toString());
        }
    }


    private void afficheK(CharSequence s) {
        EditText kelvin = (EditText) findViewById(R.id.eKelvin);

        if (kelvin.hasFocus() && s.toString().matches("[0-9][0-9.]*")) {

            Double vKelvin = Double.valueOf(s.toString());

            EditText celcius = (EditText) findViewById(R.id.eCelcius);
            EditText farenheit = (EditText) findViewById(R.id.eFarenheit);
            Log.i("k2c C : ", vKelvin.toString());
            Double vCelcius = k2c(vKelvin);
            Double vFarenheit = c2f(vCelcius);
            celcius.setText(vCelcius.toString());
            farenheit.setText(vFarenheit.toString());
        }
    }

    public void afficheC(CharSequence s) {
        EditText celcius = (EditText) findViewById(R.id.eCelcius);
        if (celcius.hasFocus() && s.toString().matches("[-]?[0-9][0-9.]*")) {
            Double vCelcius = Double.valueOf(s.toString());

            EditText kelvin = (EditText) findViewById(R.id.eKelvin);
            EditText farenheit = (EditText) findViewById(R.id.eFarenheit);
            Double vFarenheit = c2f(vCelcius);
            farenheit.setText(vFarenheit.toString());
            Double vKelvin = f2k(vFarenheit);
            kelvin.setText(vKelvin.toString());
        }
        celcius.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });

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
