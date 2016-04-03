package com.example.thierry.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thierry on 30/03/16.
 */
public class TodoListAdapter<T> extends ArrayAdapter<TodoListObject> {

    Context context;
    ArrayList<TodoListObject> tlo;


    public TodoListAdapter(Context context, int resource, ArrayList<TodoListObject> objects) {
        super(context, resource, objects);
        this.context = context;
        this.tlo = objects;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.element_dans_liste, null);
        }

        final TodoListObject p = getItem(position);

        if (p != null) {
            TextView action = (TextView) v.findViewById(R.id.action);
            TextView deadline = (TextView) v.findViewById(R.id.deadline);
            TextView priorité = (TextView) v.findViewById(R.id.priorité);
            final CheckBox status = (CheckBox) v.findViewById(R.id.status);

            if (action != null) {
                action.setText(p.getAction());
            }

            if (deadline != null) {
                deadline.setText(p.getDeadlineText());
            }

            if (priorité != null) {
                //priorité.setText(p.getPriorité());
                switch (p.getPriorité()) {
                    case R.integer.priorité_faible : v.setBackgroundResource(R.color.faible);
                        priorité.setText(R.string.priorité_faible);
                        break;
                    case R.integer.priorité_haute : v.setBackgroundResource(R.color.haute);
                        priorité.setText(R.string.priorité_haute);
                        break;
                    default : v.setBackgroundResource(R.color.normal);
                        priorité.setText(R.string.priorité_normal);
                        break;
                }
            }

            if (status != null) {
                status.setChecked(p.getStatus());
                status.setText(verifStatus(status.isChecked()));
                status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("status",String.valueOf(status.isChecked()));
                        p.setStatus(status.isChecked());
                        verifStatus(status.isChecked());
                        notifyDataSetChanged();
                        TodoListLoader.ecrire(context,tlo);
                    }
                });
            }

        }

        return v;
    }

    private int verifStatus(Boolean checked) {
        if (checked) {
            return R.string.fait;
        }
        else {
            return R.string.a_faire;
        }

    }

}
