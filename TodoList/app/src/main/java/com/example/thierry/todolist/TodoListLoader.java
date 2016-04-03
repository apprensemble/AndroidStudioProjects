package com.example.thierry.todolist;

import android.content.Context;
import android.graphics.Path;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by thierry on 03/04/16.
 */
public class TodoListLoader {
    public static void ecrire (Context context,ArrayList<TodoListObject> tlo) {
        //load file xxxx toujours le meme
        String filename = "ma_tdl";
        FileOutputStream os;
        File file = new File(context.getFilesDir(), filename);
        try {
            os = context.openFileOutput(filename, Context.MODE_PRIVATE);
            for (TodoListObject tache : tlo) {
                os.write(tache.toString().getBytes());
                os.write("\n".getBytes());
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<TodoListObject> lire(Context context) {
        String filename = "ma_tdl";
        ArrayList<TodoListObject> tlo = new ArrayList<>();
        File file = new File(context.getFilesDir(), filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String ligne;
            while ((ligne = br.readLine()) != null ) {
                Log.i("ligne", ligne);
                String[] detail = TextUtils.split(ligne,";");
                tlo.add(new TodoListObject(detail[0],detail[1],Integer.valueOf(detail[3]),Boolean.valueOf(detail[2])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tlo;
    }

}
