package com.generationminusone.wordcount2015;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Proj_List_Fragment extends Fragment implements AdapterView.OnItemClickListener {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;
    public Long mRowId;

    Cursor projCursor;

    private MyDBHandler dbHandler;

    public Proj_List_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_proj_list, null);
        Log.d("Rob Debug", "Arrived in Proj_List_Fragment; inflating view");

        dbHandler = new MyDBHandler(getActivity(), null, null, 1);

        ActionBar actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        // Initialise RecyclerView
//        projRecyclerView = (RecyclerView) rootView.findViewById(R.id.proj_recycler_view);
//        projRecyclerView.setLayoutManager(getLayoutManager());
//        projRecyclerView.setAdapter(getAdapter());

        getProjects(rootView);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.proj_list_actionbar_actions, menu);
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

//    private RecyclerView.LayoutManager getLayoutManager() {
//        return new LinearLayoutManager(getActivity());
//    }

//    private RecyclerView.Adapter getAdapter() {
//        mBasicListAdapter = new BasicListAdapter(getActivity());
//        getProjects();
//        Log.d("Rob Debug","Finished getProjects() call");
//        mBasicListAdapter.setOnItemClickListener(new BasicListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BasicListAdapter.Entity entity) {
//                System.out.println("BasicListActivity.onItemClick entity : " + entity);
//                frag = new Proj_Info_Fragment();
//                fragTransaction = getFragmentManager().beginTransaction();
//                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
//                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
//                fragTransaction.replace(R.id.container, frag);
//                fragTransaction.addToBackStack(null);
//                fragTransaction.commit();
//            }
//        });
//        return mBasicListAdapter;
//    }

    private void getProjects(View v) {
        Log.d("Rob Debug","About To Fetch Profiles");
        Cursor projCursor = dbHandler.fetchAllProfiles(); //TODO: Do I need to set up a Loader for this cursor?

        Log.d("Rob Debug","About To Initialise Array");
        // Create an array to specify the fields we want to display in the list
        String[] from = new String[]{dbHandler.KEY_NAME, dbHandler.KEY_RANKNUM};

        // and an array of the fields from proj_list_row we want to bind those fields to
        int[] to = new int[]{R.id.text1, R.id.text2};

        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter proj = new SimpleCursorAdapter(getActivity(), R.layout.proj_list_row, projCursor, from, to, 0);
        // RH Note: Refer back to MyCursorAdapter implementation in WordCount2013 for advanced functionality
        // If you use it, you'll need to add the 0 final argument to the MyCursorAdapter constructor to avoid deprecation

        ListView projListView = (ListView) v.findViewById(R.id.projListView);
        Log.d("Rob Debug","About To Set List Adapter");
        projListView.setAdapter(proj);
        Log.d("Rob Debug","List Adapter Set");
        projListView.setOnItemClickListener(this);

        Log.d("Rob Debug","onClickListener Set");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ((Main_Activity)getActivity()).projID = id;
        frag = new Proj_Detail_Fragment();
        fragTransaction = getFragmentManager().beginTransaction();
        fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
        fragTransaction.replace(R.id.container, frag);
        fragTransaction.addToBackStack(null);
        fragTransaction.commit();
    }
}
