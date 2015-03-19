package com.generationminusone.wordcount2015;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Proj_List_Fragment extends Fragment {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;

    RecyclerView projRecyclerView;
    private int mEntityCounter = 0;
    private List<BasicListAdapter.Entity> mData = new ArrayList<BasicListAdapter.Entity>();
    private BasicListAdapter mBasicListAdapter;

    //LinearLayoutManager layoutManager;
    //Cursor projCursor;
    //CursorRecyclerAdapter projAdapter;
    //ArrayList<String> projects;

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
        projRecyclerView = (RecyclerView) rootView.findViewById(R.id.proj_recycler_view);
        projRecyclerView.setLayoutManager(getLayoutManager());
        projRecyclerView.setAdapter(getAdapter());

        //projRecycler = (RecyclerView) rootView.findViewById(R.id.proj_recycler_view);

        //layoutManager = new LinearLayoutManager(getActivity());
        //projRecycler.setLayoutManager(layoutManager);
        //projRecycler.setItemAnimator(new DefaultItemAnimator());

        //Log.d("Rob Debug","About To Fetch Profiles");
        //projCursor = dbHandler.fetchAllProfiles();

        //projAdapter = new CursorRecyclerAdapter(projCursor);

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

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    private RecyclerView.Adapter getAdapter() {
        mBasicListAdapter = new BasicListAdapter(getActivity());
        addData(15, 0);

        mBasicListAdapter.setData(new ArrayList<BasicListAdapter.Entity>(mData));
        mBasicListAdapter.setOnItemClickListener(new BasicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BasicListAdapter.Entity entity) {
                System.out.println("BasicListActivity.onItemClick entity : " + entity);
            }
        });
        return mBasicListAdapter;
    }

    private void addData(int add, int del) {
        for (int i = 0; i < del; ++i) {
            int r = (int) (Math.random() * mData.size());
            mData.remove(r);
        }

        for (int i = 0; i < add; ++i) {
            int r = (int) (Math.random() * mData.size());
            mData.add(r, new BasicListAdapter.Entity("Item " + (++mEntityCounter)));
        }

        mBasicListAdapter.setData(new ArrayList<BasicListAdapter.Entity>(mData));
    }
}
