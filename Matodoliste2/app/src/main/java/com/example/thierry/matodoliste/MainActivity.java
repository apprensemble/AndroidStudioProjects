package com.example.thierry.matodoliste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.text.style.TtsSpan;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private ListView listeDeNawak;
    private ArrayAdapter<TodoList> nawakAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listeDeNawak = (ListView) findViewById(R.id.listeTaches);
        TodoList action1 = new TodoList(new GregorianCalendar().getTime(),"faire un coucou");
        TodoList action2 = new TodoList(new GregorianCalendar().getTime(),"ne rien faire");
        ArrayList<TodoList> pListe = new ArrayList<>();
        pListe.add(action1);
        pListe.add(action2);
        nawakAdapter = new ArrayAdapter<TodoList>(this,android.R.layout.simple_list_item_checked, pListe);
        listeDeNawak.setAdapter(nawakAdapter);
        listeDeNawak.setTextFilterEnabled(true);
    }
}
