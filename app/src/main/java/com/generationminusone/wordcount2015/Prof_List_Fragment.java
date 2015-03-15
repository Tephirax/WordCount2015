package com.generationminusone.wordcount2015;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Prof_List_Fragment extends Fragment {
    TextView idView;
    EditText nameBox;
    EditText rankBox;

    public Prof_List_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prof_list, null);
        Log.d("Rob Debug", "Arrived in Prof_List_Fragment; inflating view");

        idView = (TextView) rootView.findViewById(R.id.profile_ID);
        nameBox = (EditText) rootView.findViewById(R.id.profile_name);
        rankBox = (EditText) rootView.findViewById(R.id.rankNum);

        Button btnAdd = (Button) rootView.findViewById(R.id.button1);
        Button btnFind = (Button) rootView.findViewById(R.id.button2);
        Button btnDelete = (Button) rootView.findViewById(R.id.button3);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Rob Debug","About to call newProfile");
                newProfile(v);
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookupProfile(v);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProfile(v);
            }
        });

        return rootView;

    }

    public void newProfile (View view) {
        Log.d("Rob Debug","About to call MyDBHandler");
        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);

        int ranknum = Integer.parseInt(rankBox.getText().toString());

        Prof_Handler profile = new Prof_Handler(nameBox.getText().toString(), ranknum);

        dbHandler.addProfile(profile);
        nameBox.setText("");
        rankBox.setText("");
    }

    public void lookupProfile (View view) {
        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);

        Prof_Handler profile = dbHandler.findProfile(nameBox.getText().toString());

        if (profile != null) {
            idView.setText(String.valueOf(profile.getID()));

            rankBox.setText(String.valueOf(profile.getRanknum()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeProfile (View view) {
        MyDBHandler dbHandler = new MyDBHandler(getActivity().getApplicationContext(), null, null, 1);

        boolean result = dbHandler.deleteProfile(
                nameBox.getText().toString());

        if (result)
        {
            idView.setText("Record Deleted");
            nameBox.setText("");
            rankBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}
