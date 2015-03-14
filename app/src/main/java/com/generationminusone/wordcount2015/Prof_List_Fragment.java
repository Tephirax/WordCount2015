package com.generationminusone.wordcount2015;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Prof_List_Fragment extends Fragment {

    public Prof_List_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prof_list, null);
        Log.d("Rob Debug", "Arrived in Prof_List_Fragment; inflating view");
        return rootView;

    }
}
