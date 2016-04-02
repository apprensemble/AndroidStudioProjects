package com.example.thierry.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TodoListMain extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    private ListView todoListeView;
    private TodoListAdapter<TodoListObject> todoListeAdapater;
    private ArrayList<TodoListObject> todoListeArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_liste);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.todoliste_toolbar);
        setSupportActionBar(myToolbar);
        todoListeView = (ListView) findViewById(R.id.todoliste);
        TodoListObject action1 = new TodoListObject(new GregorianCalendar().getTime(),"faire un coucou",R.integer.priorité_faible);
        TodoListObject action2 = new TodoListObject(new GregorianCalendar().getTime(),"ne rien faire");
        TodoListObject action3 = new TodoListObject(new GregorianCalendar().getTime(),"arreter",R.integer.priorité_haute);
        todoListeArray = new ArrayList<>();
        todoListeArray.add(action1);
        todoListeArray.add(action2);
        todoListeArray.add(action3);
        todoListeAdapater = new TodoListAdapter<>(getApplicationContext(),R.layout.element_dans_liste,todoListeArray);
        todoListeView.setAdapter(todoListeAdapater);
        todoListeView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.ajout :
                ajoutTache();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TodoListObject tlo = todoListeAdapater.getItem(position);
        //Toast.makeText(view.getContext(),tlo.getAction(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,TodoListDetail.class);
        intent.putExtra("priorité",tlo.getPriorité());
        intent.putExtra("action",tlo.getAction());
        intent.putExtra("status",tlo.getStatus());
        intent.putExtra("deadline", tlo.getDeadline().getTime());
        intent.putExtra("change",true);
        intent.putExtra("position",position);
        startActivityForResult(intent, Intent.FILL_IN_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Intent.FILL_IN_DATA) {
            if (resultCode == RESULT_OK) {
                int position = data.getIntExtra("position",-1);
                Date d = new Date();
                d = ConvertisseurDate.dateFromSlashStr(new SimpleDateFormat("dd/MM/yyyy"),data.getStringExtra("deadline"));
                TodoListObject tlo2 = new TodoListObject(d,data.getStringExtra("action"),data.getIntExtra("priorité",R.integer.priorité_normal));
                tlo2.setStatus(data.getBooleanExtra("status", false));
                if (position != -1) {
                    TodoListObject tlo = todoListeAdapater.getItem(position);
                    tlo.setAction(String.valueOf(data.getStringExtra("action")));
                    todoListeAdapater.remove(tlo);
                }
                todoListeAdapater.add(tlo2);
                Toast.makeText(getApplicationContext(),tlo2.getDeadlineText()+" "+tlo2.getAction(),Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),R.string.annulation_ajout,Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onClick(View v) {
        ajoutTache();
    }

    private void ajoutTache() {
        Intent intent = new Intent(this,TodoListDetail.class);
        intent.putExtra("position",-1);
        startActivityForResult(intent, Intent.FILL_IN_DATA);
    }
}
