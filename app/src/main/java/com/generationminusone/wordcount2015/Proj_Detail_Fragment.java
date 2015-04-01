package com.generationminusone.wordcount2015;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Proj_Detail_Fragment extends Fragment {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;
    public Long mRowId;

    Cursor projCursor;

    private MyDBHandler dbHandler;
    private TextView name1;

    public Proj_Detail_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_proj_detail, null);
        Log.d("Rob Debug", "Arrived in Proj_Detail_Fragment; inflating view");

        dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        mRowId = ((Main_Activity)getActivity()).projId;

        name1 = (TextView) rootView.findViewById(R.id.name);
        Log.d("Rob Debug", "About To Get Project Info for row " + String.valueOf(mRowId));
        getProjectInfo(rootView, mRowId);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.proj_detail_actionbar_actions, menu);
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

    private void getProjectInfo(View v, Long mRowId) {
        if (mRowId != null) {
            Log.d("Rob Debug", "About To Fetch Profile");
            Prof_Handler profile = dbHandler.fetchProfile(mRowId);
            Log.d("Rob Debug", "Got profile: " + profile.getName());
            name1.setText(profile.getName());
        }
    }
}
