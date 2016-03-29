package com.example.thierry.matodoliste;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by thierry on 28/03/16.
 */
public class TodoVision extends Fragment {

    public TodoVision() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getActivity().getApplicationContext();
        View ecran = inflater.inflate(R.layout.todo_vision, container, false);
        return ecran;
    }
}
