package com.generationminusone.wordcount2015;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main_Activity extends ActionBarActivity {

    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new MyDBHandler(this, null, null, 1);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Main_Fragment())
                    .commit();
        }

    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                fragManager = getSupportFragmentManager();
//                if (fragManager.getBackStackEntryCount() > 0) {
//                    fragManager.popBackStack();
//                }
//                return true;
//            case R.id.action_search:
//                frag = new Prof_List_Fragment();
//                fragTransaction = getSupportFragmentManager().beginTransaction();
//                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
//                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
//                fragTransaction.replace(R.id.container, frag);
//                fragTransaction.addToBackStack(null);
//                fragTransaction.commit();
//                return true;
//            case R.id.action_projects:
//                frag = new Proj_List_Fragment();
//                fragTransaction = getSupportFragmentManager().beginTransaction();
//                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
//                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
//                fragTransaction.replace(R.id.container, frag);
//                fragTransaction.addToBackStack(null);
//                fragTransaction.commit();
//            case R.id.action_profiles:
//                frag = new Prof_List_Fragment();
//                fragTransaction = getSupportFragmentManager().beginTransaction();
//                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
//                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
//                fragTransaction.replace(R.id.container, frag);
//                fragTransaction.addToBackStack(null);
//                fragTransaction.commit();
//                return true;
//            case R.id.action_settings: /* TODO: Need to create a settings screen and add link to it here */
//                frag = new Prof_List_Fragment();
//                fragTransaction = getSupportFragmentManager().beginTransaction();
//                fragTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
//                        R.anim.pop_slide_in_left, R.anim.pop_slide_in_right);
//                fragTransaction.replace(R.id.container, frag);
//                fragTransaction.addToBackStack(null);
//                fragTransaction.commit();
//                return true;
//            case R.id.action_help:
//                Log.d("Rob Debug", "About to call Intent to Help");
//                Intent l = new Intent(this, Help_Activity.class);
//                startActivity(l);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
