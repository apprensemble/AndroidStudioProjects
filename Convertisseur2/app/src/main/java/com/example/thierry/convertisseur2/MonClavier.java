package com.example.thierry.convertisseur2;

import android.app.Activity;
import android.inputmethodservice.KeyboardView;
import android.os.Vibrator;
import android.view.KeyEvent;

/**
 * Created by thierry on 16/03/16.
 */
public class MonClavier implements KeyboardView.OnKeyboardActionListener{
    private Activity monActivite;
    private Vibrator vibreur;
    public MonClavier(Activity monActivite) {
        this.monActivite = monActivite;

        vibreur = (Vibrator) monActivite.getSystemService(monActivite.VIBRATOR_SERVICE);
    }
    @Override
    public void onPress(int primaryCode) {

        KeyEvent event = new KeyEvent(KeyEvent.ACTION_MULTIPLE,primaryCode);

        vibreur.vibrate(25);
        monActivite.dispatchKeyEvent(event);

    }


    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {

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
}
