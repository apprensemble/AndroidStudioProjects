package com.example.thierry.matodoliste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView listeDeNawak;
    private ArrayAdapter<String> nawakAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listeDeNawak = (ListView) findViewById(R.id.listView);
        nawakAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, Arrays.asList("a","b","c"));
        listeDeNawak.setAdapter(nawakAdapter);
        listeDeNawak.setTextFilterEnabled(true);
    }
}
