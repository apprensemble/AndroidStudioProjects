package com.example.thierry.matodoliste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thierry on 23/03/16.
 */
public class TodoAdapter<T> extends ArrayAdapter<TodoList> {

    public TodoAdapter(Context context, int resource, ArrayList<TodoList> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.tache_dans_liste, null);
        }

        TodoList p = getItem(position);

        if (p != null) {
            TextView action = (TextView) v.findViewById(R.id.action);
            TextView deadline = (TextView) v.findViewById(R.id.deadline);
            TextView priorité = (TextView) v.findViewById(R.id.priorité);
            CheckBox status = (CheckBox) v.findViewById(R.id.status);

            if (action != null) {
                action.setText(p.getAction());
            }

            if (deadline != null) {
                deadline.setText(p.getDeadline().toString());
            }

            if (priorité != null) {
                priorité.setText(p.getPriorité());
            }

            if (status != null) {
                status.setChecked(p.getStatus());
            }

        }

        return v;
    }

}
