package com.generationminusone.wordcount2015;

import android.content.Intent;
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
import android.widget.Button;
//import android.net.Uri;
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.widget.EditText;
//import android.widget.Toast;

public class Main_Fragment extends Fragment {

    Fragment frag;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;

    public Main_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Log.d("Rob Debug", "Inflating Main_Fragment view");

        ActionBar actionBar = ((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_actionbar_actions, menu);
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
            case R.id.action_search:
                frag = new Prof_List_Fragment();
                fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
                fragTransaction.replace(R.id.container, frag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
                return true;
            case R.id.action_projects:
                frag = new Proj_List_Fragment();
                fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
                fragTransaction.replace(R.id.container, frag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
                return true;
            case R.id.action_profiles:
                frag = new Prof_List_Fragment();
                fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
                fragTransaction.replace(R.id.container, frag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
                return true;
            case R.id.action_settings: /* TODO: Need to create a settings screen and add link to it here */
                frag = new Prof_List_Fragment();
                fragTransaction = getFragmentManager().beginTransaction();
                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
                fragTransaction.replace(R.id.container, frag);
                fragTransaction.addToBackStack(null);
                fragTransaction.commit();
                return true;
            case R.id.action_help: // TODO: Set up ViewPager fragment to contain help pages;
//                Log.d("Rob Debug", "About to call Intent to Help");
//                Intent l = new Intent(this, Help_Activity.class);
//                startActivity(l);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}