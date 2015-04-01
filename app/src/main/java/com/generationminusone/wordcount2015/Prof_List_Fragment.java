package com.generationminusone.wordcount2015;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Prof_List_Fragment extends Fragment implements AdapterView.OnItemClickListener {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;
    public Long mRowId;

    Cursor projCursor;

    private MyDBHandler dbHandler;

    public Prof_List_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prof_list, null);
        Log.d("Rob Debug", "Arrived in Prof_List_Fragment; inflating view");

        dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        // If no profiles exist, go immediately to Create Profile fragment;
        int count = dbHandler.countAllProfiles();
        if (count == 0) {
            goToCreateProfile();
        }

        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        getProfiles(rootView);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.prof_list_actionbar_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                fragManager = getFragmentManager();
                if (fragManager.getBackStackEntryCount() > 0) {
                    fragManager.popBackStack();
                }
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void goToCreateProfile() {
        frag = new Prof_Detail_Fragment();
        fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.container, frag);
        fragTransaction.addToBackStack(null);
        fragTransaction.commit();
    }

    private void getProfiles(View v) {
        Log.d("Rob Debug","About To Fetch Profiles");
        Cursor profCursor = dbHandler.fetchAllProfiles(); //TODO: Do I need to set up a Loader for this cursor?

        Log.d("Rob Debug","About To Initialise Array");
        // Create an array to specify the fields we want to display in the list
        String[] from = new String[]{dbHandler.KEY_PROFILENAME, dbHandler.KEY_RANKNUM};

        // and an array of the fields from proj_list_row we want to bind those fields to
        int[] to = new int[]{R.id.text1, R.id.text2};

        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter prof = new SimpleCursorAdapter(getActivity(), R.layout.prof_list_row, profCursor, from, to, 0);
        // RH Note: Refer back to MyCursorAdapter implementation in WordCount2013 for advanced functionality
        // (eg. highlighting & flagging Active Profile)
        // If you use it, you'll need to add the 0 final argument to the MyCursorAdapter constructor to avoid deprecation

        ListView profListView = (ListView) v.findViewById(R.id.profListView);
        Log.d("Rob Debug","About To Set List Adapter");
        profListView.setAdapter(prof);
        Log.d("Rob Debug","List Adapter Set");
        profListView.setOnItemClickListener(this);

        Log.d("Rob Debug","onClickListener Set");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Main_Activity)getActivity()).profId = id;
        frag = new Prof_Detail_Fragment();
        fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
        fragTransaction.replace(R.id.container, frag);
        fragTransaction.addToBackStack(null);
        fragTransaction.commit();
    }
}

