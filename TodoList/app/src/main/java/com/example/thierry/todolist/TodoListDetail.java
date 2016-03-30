package com.example.thierry.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

public class TodoListDetail extends AppCompatActivity  implements DialogInterface.OnClickListener, View.OnClickListener {
    private CheckBox statusView;
    private RadioGroup prioritéView;
    private EditText actionView;
    private EditText deadlineView;
    private Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        setContentView(R.layout.detail);
        prioritéView = (RadioGroup) findViewById(R.id.groupe_priorité);
        RadioButton prioritéf = (RadioButton) findViewById(R.id.priorité_faible);
        RadioButton prioritén = (RadioButton) findViewById(R.id.priorité_normal);
        RadioButton prioritéh = (RadioButton) findViewById(R.id.priorité_haute);
        statusView = (CheckBox) findViewById(R.id.status);
        actionView = (EditText) findViewById(R.id.action);
        deadlineView = (EditText) findViewById(R.id.deadline );
        Button ok = (Button) findViewById(R.id.valide);
        Button annule = (Button) findViewById(R.id.annule);
        Date d = new Date();
        d.setTime(b.getLong("deadline",-1));
        deadlineView.setText(d.toString());
        //savedInstanceState.get("action");
        actionView.setText(b.getString("action","entrez une action"));
        //deadlineView.setText("coucou");
        switch (b.getInt("priorité",R.integer.priorité_normal)) {
            case R.integer.priorité_faible :
                actionView.setBackgroundResource(R.color.faible);
                prioritéView.setBackgroundResource(R.color.faible);
                ok.setBackgroundResource(R.color.faible);
                prioritéView.check(R.id.priorité_faible);
                break;
            case R.integer.priorité_haute :
                actionView.setBackgroundResource(R.color.haute);
                prioritéView.setBackgroundResource(R.color.haute);
                ok.setBackgroundResource(R.color.haute);
                prioritéView.check(R.id.priorité_haute);
                break;
            case R.integer.priorité_normal :
                actionView.setBackgroundResource(R.color.normal);
                prioritéView.setBackgroundResource(R.color.normal);
                ok.setBackgroundResource(R.color.normal);
                prioritéView.check(R.id.priorité_normal);
                break;
        }
        statusView.setChecked(b.getBoolean("status",false));
        position = b.getInt("position");
        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }

    @Override
    public void onClick(View v) {
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
        i.putExtra("deadline",deadlineView.getText());
        setResult(TodoListMain.RESULT_OK, i);
        finish();
    }
}
