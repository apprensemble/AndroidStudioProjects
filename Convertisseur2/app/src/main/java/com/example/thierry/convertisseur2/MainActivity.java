package com.example.thierry.convertisseur2;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ControllerConv monController;
    private VueConv maVue;
    private ModelConv monModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monModel = new ModelConv();
        maVue = new VueConv(this);
        monController = new ControllerConv(maVue, monModel);
        maVue.setMonController(monController);

        KeyboardView kbd = (KeyboardView) findViewById(R.id.keyboardview);
        kbd.setKeyboard(new Keyboard(this, R.xml.keyboard));
        kbd.setOnKeyboardActionListener(new MonClavier(this));

    }
}
