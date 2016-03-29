package com.example.thierry.matodoliste;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listeDeNawak;
    private TodoAdapter<TodoList> nawakAdapter;
    private ArrayList<TodoList> pListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_vision);
        listeDeNawak = (ListView) findViewById(R.id.listeTaches);
        TodoList action1 = new TodoList(new GregorianCalendar().getTime(),"faire un coucou",R.integer.priorité_faible);
        TodoList action2 = new TodoList(new GregorianCalendar().getTime(),"ne rien faire");
        pListe = new ArrayList<>();
        for (int i = 0;i<1;i++) {
            pListe.add(action1);
            pListe.add(action2);
        }
        nawakAdapter = new TodoAdapter<>(getApplicationContext(),R.layout.tache_dans_liste,pListe);
        listeDeNawak.setAdapter(nawakAdapter);
        //listeDeNawak.setTextFilterEnabled(true);
        listeDeNawak.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TodoList tl = nawakAdapter.getItem(position);
        Toast.makeText(view.getContext(), tl.getAction(), Toast.LENGTH_SHORT).show();
        Fragment f = new TodoVision();
        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.le_detail,f);
        //t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        t.addToBackStack(null);
        t.commit();
    }

}
