package com.example.thierry.convertisseur2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.net.Uri;
import android.os.Vibrator;
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

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import static com.example.thierry.convertisseur2.Convertisseur.c2f;
import static com.example.thierry.convertisseur2.Convertisseur.f2k;
import static com.example.thierry.convertisseur2.Convertisseur.k2c;
import static java.lang.Integer.getInteger;
import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    private EditText celcius, farenheit, kelvin;
    private Double vFarenheit, vCelcius, vKelvin;
    private TextView message;
    private DecimalFormat deuxDigit;
    private Vibrator vibreur;
    final private long [] vibAlerte = {0,50,100,100};

    private ControllerConv monController;
    private VueConv maVue;
    private ModelConv monModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        celcius = (EditText) findViewById(R.id.eCelcius);
        farenheit = (EditText) findViewById(R.id.eFarenheit);
        kelvin = (EditText) findViewById(R.id.eKelvin);
        message = (TextView) findViewById(R.id.message);
        vibreur = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        monModel = new ModelConv();
        maVue = new VueConv(this);
        monController = new ControllerConv(maVue, monModel);
        maVue.setMonController(monController);


        KeyboardView kbd = (KeyboardView) findViewById(R.id.keyboardview);
        kbd.setKeyboard(new Keyboard(this, R.xml.keyboard));
        kbd.setOnKeyboardActionListener(new MonClavier(this));



    }





}
