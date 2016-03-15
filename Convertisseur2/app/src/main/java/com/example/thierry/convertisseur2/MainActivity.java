package com.example.thierry.convertisseur2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.lang.Integer.getInteger;

public class MainActivity extends AppCompatActivity {

    private EditText celcius, farenheit, kelvin;
    private Double vFarenheit, vCelcius, vKelvin;
    private TextView message;
    private DecimalFormat deuxDigit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        celcius = (EditText) findViewById(R.id.eCelcius);
        farenheit = (EditText) findViewById(R.id.eFarenheit);
        kelvin = (EditText) findViewById(R.id.eKelvin);
        message = (TextView) findViewById(R.id.message);
        deuxDigit = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

        OnTouchListener neFaisRien = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText et = (EditText) v;
                et.setSelection(et.getOffsetForPosition(event.getX(), event.getY()));
                v.requestFocus();
                //((EditText) v).getOffsetForPosition(event.getX(), event.getY());
                //Log.i("focus","event "+((EditText) v).getOffsetForPosition(event.getX(), event.getY()));
                return true;
            }
        };

        kelvin.setOnTouchListener(neFaisRien);
        farenheit.setOnTouchListener(neFaisRien);
        celcius.setOnTouchListener(neFaisRien);


        KeyboardView kbd = (KeyboardView) findViewById(R.id.keyboardview);
        kbd.setKeyboard(new Keyboard(this, R.xml.keyboard));
        kbd.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {
                KeyEvent event = new KeyEvent(KeyEvent.ACTION_MULTIPLE,primaryCode);
                dispatchKeyEvent(event);

            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                /*
                long eventTime = System.currentTimeMillis();
                KeyEvent event = new KeyEvent(eventTime, eventTime,
                        KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0,
                        KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
                dispatchKeyEvent(event);
                */

            }

            @Override
            public void onText(CharSequence text) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });

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

        kelvin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (kelvin.hasFocus()) {
                    convertirDepuisKelvin(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    kelvinValide(Double.valueOf(vKelvin) >= 0);
                }
                catch (NullPointerException e) {

                }

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
        vKelvin = 0.0;
        kelvinValide(true);
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
        editText.setText(deuxDigit.format(v));
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
