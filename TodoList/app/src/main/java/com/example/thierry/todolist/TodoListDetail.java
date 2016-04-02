package com.example.thierry.todolist;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class TodoListDetail extends AppCompatActivity  implements DialogInterface.OnClickListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private CheckBox statusView;
    private RadioGroup prioritéView;
    private EditText actionView;
    private EditText deadlineView;
    private Integer position;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.detail3);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        Log.i("toolbar : ", String.valueOf(myToolbar.getTitle()));
        setSupportActionBar(myToolbar);
        prioritéView = (RadioGroup) findViewById(R.id.groupe_priorité);
        RadioButton prioritéf = (RadioButton) findViewById(R.id.priorité_faible);
        RadioButton prioritén = (RadioButton) findViewById(R.id.priorité_normal);
        RadioButton prioritéh = (RadioButton) findViewById(R.id.priorité_haute);
        statusView = (CheckBox) findViewById(R.id.status);
        actionView = (EditText) findViewById(R.id.action);
        deadlineView = (EditText) findViewById(R.id.deadline );
        Button calendrierView = (Button) findViewById(R.id.calendrier);
        ok = (Button) findViewById(R.id.valide);
        Button annule = (Button) findViewById(R.id.annule);
        Date d = new Date();
        d.setTime(b.getLong("deadline", d.getTime()));
        deadlineView.setText(ConvertisseurDate.strSlashFromDate(d));
        //savedInstanceState.get("action");
        actionView.setText(b.getString("action","entrez une action"));
        //deadlineView.setText("coucou");
        switch (b.getInt("priorité",R.integer.priorité_normal)) {
            case R.integer.priorité_faible :
                faible();
                break;
            case R.integer.priorité_haute :
                haute();
                break;
            case R.integer.priorité_normal :
                normal();
                break;
        }
        statusView.setChecked(b.getBoolean("status",false));
        position = b.getInt("position");
        ok.setOnClickListener(this);
        deadlineView.setOnClickListener(this);
        calendrierView.setOnClickListener(this);
        prioritéf.setOnClickListener(this);
        prioritén.setOnClickListener(this);
        prioritéh.setOnClickListener(this);
        statusView.setOnClickListener(this);
        annule.setOnClickListener(this);
        verifStatus();
    }

    private void faible() {
        actionView.setBackgroundResource(R.color.faible);
        prioritéView.setBackgroundResource(R.color.faible);
        ok.setBackgroundResource(R.color.faible);
        prioritéView.check(R.id.priorité_faible);
    }

    private void normal() {
        actionView.setBackgroundResource(R.color.normal);
        prioritéView.setBackgroundResource(R.color.normal);
        ok.setBackgroundResource(R.color.normal);
        prioritéView.check(R.id.priorité_normal);
    }

    private void haute() {
        actionView.setBackgroundResource(R.color.haute);
        prioritéView.setBackgroundResource(R.color.haute);
        ok.setBackgroundResource(R.color.haute);
        prioritéView.check(R.id.priorité_haute);
    }

    private void verifStatus() {
        if (statusView.isChecked()) {
            statusView.setText(R.string.fait);
        }
        else {
            statusView.setText(R.string.a_faire);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.valide :
                validation();
                break;
            case R.id.calendrier :
                showDatePickerDialog(deadlineView);
                break;
            case R.id.priorité_faible :
                faible();
                break;
            case R.id.priorité_haute :
                haute();
                break;
            case R.id.priorité_normal :
                normal();
                break;
            case R.id.status :
                verifStatus();
                break;
            case R.id.annule :
                annulation();
                break;
        }
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void annulation() {
        setResult(TodoListMain.RESULT_CANCELED);
        finish();
    }

    private void validation() {
        Intent i = new Intent();
        i.putExtra("action", actionView.getText().toString());
        switch (prioritéView.getCheckedRadioButtonId()) {
            case R.id.priorité_faible :
                i.putExtra("priorité",R.integer.priorité_faible);
                break;
            case R.id.priorité_normal :
                i.putExtra("priorité",R.integer.priorité_normal);
                break;
            case R.id.priorité_haute :
                i.putExtra("priorité",R.integer.priorité_haute);
                break;
        }
        i.putExtra("position",position);
        i.putExtra("status",statusView.isChecked());
        i.putExtra("deadline", deadlineView.getText().toString());
        setResult(TodoListMain.RESULT_OK, i);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year,monthOfYear, dayOfMonth);
        String date = ConvertisseurDate.strSlashFromDate(cal.getTime());
        deadlineView.setText(date);
    }
}
