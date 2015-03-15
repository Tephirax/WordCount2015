package com.generationminusone.wordcount2015;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.net.Uri;
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.widget.EditText;
//import android.widget.Toast;

public class Main_Fragment extends Fragment {

    Fragment frag;
    FragmentTransaction fragTransaction;

    public Main_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d("Rob Debug", "Inflating Main_Fragment view");

        Button btnProfList = (Button) rootView.findViewById(R.id.btnProfList);
        Button btnProjList = (Button) rootView.findViewById(R.id.btnProjList);

        btnProfList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new Prof_List_Fragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
            }
        });

        btnProjList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new Proj_List_Fragment();
                fragTransaction = getFragmentManager().beginTransaction().replace(R.id.container, frag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
            }
        });

        return rootView;
    }
}