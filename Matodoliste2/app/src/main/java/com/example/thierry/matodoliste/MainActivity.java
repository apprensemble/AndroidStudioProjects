package com.example.thierry.matodoliste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private ListView listeDeNawak;
    private TodoAdapter<TodoList> nawakAdapter;
    private ArrayList<TodoList> pListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listeDeNawak = (ListView) findViewById(R.id.listeTaches);
        TodoList action1 = new TodoList(new GregorianCalendar().getTime(),"faire un coucou",R.integer.priorité_faible);
        TodoList action2 = new TodoList(new GregorianCalendar().getTime(),"ne rien faire");
        pListe = new ArrayList<>();
        for (int i = 0;i<10;i++) {
            pListe.add(action1);
            pListe.add(action2);
        }
        nawakAdapter = new TodoAdapter<>(this,R.layout.tache_dans_liste,pListe);
        listeDeNawak.setAdapter(nawakAdapter);
        listeDeNawak.setTextFilterEnabled(true);
        listeDeNawak.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " + pListe.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
