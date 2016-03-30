package com.example.thierry.matodoliste;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by thierry on 28/03/16.
 */
public class TodoListFragment  extends ListFragment {
    private ListView listeDeNawak;
    private TodoAdapter<TodoList> nawakAdapter;
    private ArrayList<TodoList> pListe;
    private int mCurCheckPosition;
    private boolean mDualPane;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View detail = getActivity().findViewById(R.id.le_vrai_detail);
        TodoList action1 = new TodoList(new GregorianCalendar().getTime(), "faire un coucou", R.integer.priorité_faible);
        TodoList action2 = new TodoList(new GregorianCalendar().getTime(), "ne rien faire");
        pListe = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            pListe.add(action1);
            pListe.add(action2);
        }
        nawakAdapter = new TodoAdapter<>(getActivity(), R.layout.tache_dans_liste, pListe);
        setListAdapter(nawakAdapter);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(mCurCheckPosition);
        }


    }

    private void showDetails(int mCurCheckPosition) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }


    void showDetails(int index) {
        mCurCheckPosition = index;

        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            getListView().setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            TodoVision details = (TodoVision)
                    getFragmentManager().findFragmentById(R.id.le_vrai_detail);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = TodoVision.newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (index == 0) {
                    ft.replace(R.id.le_vrai_detail, details);
                } else {
                    ft.replace(R.id.le_vrai_detail, details);
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        }
    }
}
